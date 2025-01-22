package com.teamconnect.service.impl;

import com.teamconnect.api.output.user.AuthorOutput;
import com.teamconnect.dto.MessageDto;
import com.teamconnect.model.nosql.Message;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.couchbase.MessageRepository;
import com.teamconnect.repository.postgresql.UserRepository;
import com.teamconnect.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDto> getMessagesByChannelId(String channelId) {
        List<Message> messageList = messageRepository.findMessageByChannelId(channelId);

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
}
