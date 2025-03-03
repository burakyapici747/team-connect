package com.teamconnect.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.api.output.user.AuthorOutput;
import com.teamconnect.dto.MessageDto;
import com.teamconnect.dto.WebSocketMessageDto;
import com.teamconnect.mapper.MessageMapper;
import com.teamconnect.model.nosql.Message;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.couchbase.MessageRepository;
import com.teamconnect.repository.postgresql.UserRepository;
import com.teamconnect.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;

    public MessageServiceImpl(
        MessageRepository messageRepository,
        UserRepository userRepository,
        RabbitTemplate rabbitTemplate
    ) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<MessageDto> getMessages(String channelId, String beforeMessageId, int limit) {
        List<Message> messageList;

        if (Objects.isNull(beforeMessageId)) {
            messageList = messageRepository.findInitialMessages(channelId, limit);
        } else {
            messageList = messageRepository.findMessagesBeforeId(channelId, beforeMessageId, limit);
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
    public MessageDto sendMessage(String channelId, String authorId,  MessageCreateInput messageCreateInput) throws JsonProcessingException {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setAuthorId(authorId);
        message.setChannelId(channelId);
        message.setContent(messageCreateInput.content());
        message.setTimestamp(Instant.now());
        message.setEditedTimestamp(null);
        message.setPinned(false);
        message.setType(1);
        message.setAttachments(null);
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

        AuthorOutput authorOutput = new AuthorOutput(
            author.getId(),
            author.getUsername(),
            author.getUserProfile().getAvatarFileUrl(),
            author.getUserProfile().getAvatarFileId()
        );



        webSocketMessageDto.setAuthor(authorOutput);

        rabbitTemplate.convertAndSend(
            "dm.exchange.dccd525d-d909-4cec-99f4-28cb459eb9a5",
            channelId,
            webSocketMessageDto,
            m -> {
                m.getMessageProperties().setHeader("channelId", channelId);
                return m;
            }
        );

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
    }
}
