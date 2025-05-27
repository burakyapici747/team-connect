package com.teamconnect.service.impl;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.api.output.user.AuthorOutput;
import com.teamconnect.common.enumarator.FilePurposeType;
import com.teamconnect.dto.MessageDto;
import com.teamconnect.dto.WebSocketMessageDto;
import com.teamconnect.model.nosql.Attachment;
import com.teamconnect.model.nosql.File;
import com.teamconnect.model.nosql.Message;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.couchbase.MessageRepository;
import com.teamconnect.repository.postgresql.UserRepository;
import com.teamconnect.service.FileService;
import com.teamconnect.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final FileService fileService;
    private final RabbitTemplate rabbitTemplate;

    public MessageServiceImpl(
        MessageRepository messageRepository,
        UserRepository userRepository,
        FileService fileService, RabbitTemplate rabbitTemplate
    ) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.fileService = fileService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<MessageDto> getMessages(String channelId, String before, String after, int limit){
        List<Message> messageList;

        if (Objects.nonNull(before)) {
            messageList = messageRepository.findMessagesBeforeId(channelId, before, limit);
        } else {
            if(Objects.nonNull(after)) {
                messageList = messageRepository.findMessagesAfterId(channelId, after, limit);
            }else{
                messageList = messageRepository.findInitialMessages(channelId, limit);
            }
        }

        Set<String> userIds = messageList.stream()
            .map(Message::getAuthorId)
            .collect(Collectors.toSet());

        List<User> userList = userRepository.findAllByIdIn(userIds);

        Map<String, User> userMap = userList.stream()
            .collect(Collectors.toMap(User::getId, Function.identity()));

        return messageList.stream()
            .map(message -> {
                User author = userMap.get(message.getAuthorId());
                String avatarFileUrl = null;
                String avatarFileId = null;

                if (author.getUserProfile() != null) {
                    avatarFileUrl = author.getUserProfile().getAvatarFileUrl();
                    avatarFileId = author.getUserProfile().getAvatarFileId();
                }

                return new MessageDto(
                    message.getId(),
                    message.getChannelId(),
                    message.getContent(),
                    message.getTimestamp(),
                    message.getEditedTimestamp(),
                    message.getPinned(),
                    message.getType(),
                    message.getAttachments(),
                    message.getMentions(),
                    message.getReactions(),
                    new AuthorOutput(
                        author.getId(),
                        author.getUsername(),
                        avatarFileUrl,
                        avatarFileId
                    )
                );
            })
            .collect(Collectors.toList());
    }

    @Override
    public MessageDto sendMessage(String channelId, String authorId,  MessageCreateInput messageCreateInput) throws IOException {
        Set<Attachment> attachmentSet = createInputFiles(channelId, messageCreateInput.getMultipartFileList());
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setAuthorId(authorId);
        message.setChannelId(channelId);
        message.setContent(messageCreateInput.getContent());
        message.setTimestamp(Instant.now().toEpochMilli());
        message.setEditedTimestamp(null);
        message.setPinned(false);
        message.setType(1);
        message.setAttachments(attachmentSet);
        message.setMentions(null);
        message.setReactions(null);
        messageRepository.save(message);

        User author = userRepository.findById(authorId).orElseThrow();

        WebSocketMessageDto webSocketMessageDto = new WebSocketMessageDto();
        webSocketMessageDto.setId(message.getId());
        webSocketMessageDto.setChannelId(message.getChannelId());
        webSocketMessageDto.setContent(message.getContent());
        webSocketMessageDto.setTimestamp(message.getTimestamp());
        webSocketMessageDto.setEditedTimestamp(message.getEditedTimestamp());
        webSocketMessageDto.setPinned(message.getPinned());
        webSocketMessageDto.setType(message.getType());
        webSocketMessageDto.setAttachments(message.getAttachments());
        webSocketMessageDto.setMentions(message.getMentions());
        webSocketMessageDto.setReactions(message.getReactions());

        rabbitTemplate.convertAndSend("chat.msg", message.getChannelId(), webSocketMessageDto,
            m -> { m.getMessageProperties().setContentType("application/json"); return m; });

        AuthorOutput authorOutput = new AuthorOutput(
            author.getId(),
            author.getUsername(),
            author.getUserProfile().getAvatarFileUrl(),
            author.getUserProfile().getAvatarFileId()
        );

        webSocketMessageDto.setAuthor(authorOutput);

        String avatarFileUrl = null;
        String avatarFileId = null;

        if (author.getUserProfile() != null) {
            avatarFileUrl = author.getUserProfile().getAvatarFileUrl();
            avatarFileId = author.getUserProfile().getAvatarFileId();
        }

        return new MessageDto(
            message.getId(),
            message.getChannelId(),
            message.getContent(),
            message.getTimestamp(),
            message.getEditedTimestamp(),
            message.getPinned(),
            message.getType(),
            message.getAttachments(),
            message.getMentions(),
            message.getReactions(),
            new AuthorOutput(author.getId(), author.getUsername(), avatarFileUrl, avatarFileId)
        );
    }

    private Set<Attachment> createInputFiles(String channelId, List<MultipartFile> fileList) throws IOException {
        Set<Attachment> messageAttachmentList = new HashSet<>();
        if(Objects.nonNull(fileList)){
            for(MultipartFile file : fileList){
                if(Objects.nonNull(file) && !file.isEmpty()){
                    File messageFile = fileService.storeFile(file, FilePurposeType.IMAGE, channelId);
                    Attachment attachment = new Attachment();
                    attachment.setId(UUID.randomUUID().toString());
                    attachment.setName(file.getOriginalFilename());
                    attachment.setTitle("");
                    attachment.setDescription("");
                    attachment.setContentType(file.getContentType());
                    attachment.setSize(file.getSize());
                    attachment.setUrl(messageFile.getFileUrl());
                    messageAttachmentList.add(attachment);
                }
            }
        }

        return messageAttachmentList;
    }
}
