package com.teamconnect.service.impl;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.dto.MessageDto;
import com.teamconnect.exception.MessageNotFoundException;
import com.teamconnect.exception.UnauthorizedAccessException;
import com.teamconnect.mapper.MessageMapper;
import com.teamconnect.model.nosql.Message;
import com.teamconnect.repository.nosql.MessageRepository;
import com.teamconnect.service.MessageService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageDto createMessage(MessageCreateInput input, String senderId) {
        Message message = messageMapper.messageCreateInputToMessage(input);
        message.setId("msg_" + UUID.randomUUID().toString());
        message.setSenderId(senderId);
        message.setCreatedAt(Instant.now());
        message.setUpdatedAt(Instant.now());
        
        Message savedMessage = messageRepository.save(message);
        return messageMapper.messageToMessageDto(savedMessage);
    }

    @Override
    public MessageDto getMessage(String messageId) {
        Message message = findMessageById(messageId);
        return messageMapper.messageToMessageDto(message);
    }

    @Override
    public List<MessageDto> getMessagesByReceiverId(String receiverId, Long timestamp, int limit) {
        Instant since = timestamp != null ? Instant.ofEpochMilli(timestamp) : null;
        List<Message> messages = since != null 
            ? messageRepository.findByReceiverIdAndIsDeletedFalseAndCreatedAtBeforeOrderByCreatedAtDesc(receiverId, since, limit)
            : messageRepository.findByReceiverIdAndIsDeletedFalseOrderByCreatedAtDesc(receiverId, limit);
        return messageMapper.messagesToMessageDtos(messages);
    }

    @Override
    public List<MessageDto> getMessagesBySenderId(String senderId, Long timestamp, int limit) {
        Instant since = timestamp != null ? Instant.ofEpochMilli(timestamp) : null;
        List<Message> messages = since != null
            ? messageRepository.findBySenderIdAndIsDeletedFalseAndCreatedAtBeforeOrderByCreatedAtDesc(senderId, since, limit)
            : messageRepository.findBySenderIdAndIsDeletedFalseOrderByCreatedAtDesc(senderId, limit);
        return messageMapper.messagesToMessageDtos(messages);
    }

    @Override
    public void deleteMessage(String messageId, String userId) {
        Message message = findMessageById(messageId);
        validateMessageOwnership(message, userId);
        message.setIsDeleted(true);
        message.setUpdatedAt(Instant.now());
        messageRepository.save(message);
    }

    @Override
    public List<MessageDto> searchMessages(String userId, String query, int limit) {
        List<Message> messages = messageRepository.searchMessages(userId, query, limit);
        return messageMapper.messagesToMessageDtos(messages);
    }

    @Override
    public Long getUnreadMessageCount(String userId) {
        return messageRepository.countUnreadMessages(userId);
    }

    private Message findMessageById(String messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException("Message not found with id: " + messageId));
    }

    private void validateMessageOwnership(Message message, String userId) {
        if (!message.getSenderId().equals(userId)) {
            throw new UnauthorizedAccessException("You don't have permission to modify this message");
        }
    }
} 