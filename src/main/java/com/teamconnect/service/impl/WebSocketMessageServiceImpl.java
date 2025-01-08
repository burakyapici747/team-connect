package com.teamconnect.service.impl;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.configuration.RabbitMQConfig;
import com.teamconnect.mapper.MessageMapper;
import com.teamconnect.mapper.WebSocketMessageMapper;
import com.teamconnect.model.websocket.WebSocketMessage;
import com.teamconnect.model.websocket.WebSocketMessageStatus;
import com.teamconnect.service.MessageService;
import com.teamconnect.service.WebSocketMessageService;
import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketMessageServiceImpl implements WebSocketMessageService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketMessageServiceImpl.class);
    private static final String USER_QUEUE_MESSAGES = "/queue/messages";
    private static final String USER_QUEUE_MESSAGE_STATUS = "/queue/message.status";
    private static final String TOPIC_STATUS = "/topic/status";

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final RedisMessageService redisMessageService;
    private final WebSocketMessageMapper webSocketMessageMapper;
    private final MessageMapper messageMapper;
    private final RabbitMQConfig rabbitMQConfig;
    private final RabbitAdmin rabbitAdmin;

    public WebSocketMessageServiceImpl(
        SimpMessagingTemplate messagingTemplate,
        MessageService messageService,
        RedisMessageService redisMessageService,
        WebSocketMessageMapper webSocketMessageMapper,
        MessageMapper messageMapper,
        RabbitMQConfig rabbitMQConfig,
        RabbitAdmin rabbitAdmin
    ) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.redisMessageService = redisMessageService;
        this.webSocketMessageMapper = webSocketMessageMapper;
        this.messageMapper = messageMapper;
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitAdmin = rabbitAdmin;
    }

    @Override
    public void sendPrivateMessage(WebSocketMessage message, String senderId) {
        WebSocketMessage enrichedMessage = webSocketMessageMapper.enrichWebSocketMessage(message, senderId);
        MessageCreateInput messageCreateInput = webSocketMessageMapper.webSocketMessageToMessageCreateInput(enrichedMessage);
        
        // Create private chat queue if it doesn't exist
        Queue privateQueue = rabbitMQConfig.createPrivateChatQueue(
            Long.parseLong(senderId), 
            Long.parseLong(message.receiverId())
        );
        rabbitMQConfig.declareQueue(privateQueue, rabbitAdmin);
        
        messageService.createMessage(messageCreateInput, senderId);
        deliverMessage(enrichedMessage);
    }

    @Override
    public void updateMessageStatus(WebSocketMessageStatus status, String userId) {
        notifyMessageStatus(status);
    }

    @Override
    public void sendMessageStatus(String messageId, String userId, WebSocketMessageStatus.MessageDeliveryStatus status) {
        WebSocketMessageStatus messageStatus = createMessageStatus(messageId, userId, status);
        notifyMessageStatus(messageStatus);
    }

    @Override
    public void handleOfflineMessages(String userId) {
        List<WebSocketMessage> offlineMessages = redisMessageService.getAndRemoveOfflineMessages(userId);
        deliverOfflineMessages(userId, offlineMessages);
    }

    @Override
    public void handleUserStatusChange(String userId, boolean isOnline) {
        String status = isOnline ? "ONLINE" : "OFFLINE";
        messagingTemplate.convertAndSend(
            TOPIC_STATUS,
            new UserStatusMessage(userId, status)
        );
    }

    private void deliverMessage(WebSocketMessage message) {
        try {
            messagingTemplate.convertAndSendToUser(
                message.receiverId(),
                USER_QUEUE_MESSAGES,
                message
            );
            sendMessageStatus(message.id(), message.senderId(), WebSocketMessageStatus.MessageDeliveryStatus.SENT);
        } catch (Exception e) {
            logger.error("Failed to deliver message to user {}: {}", message.receiverId(), e.getMessage());
            redisMessageService.saveOfflineMessage(message.receiverId(), message);
        }
    }

    private void deliverOfflineMessages(String userId, List<WebSocketMessage> messages) {
        messages.forEach(message -> deliverMessage(message));
    }

    private void notifyMessageStatus(WebSocketMessageStatus status) {
        try {
            messagingTemplate.convertAndSendToUser(
                status.userId(),
                USER_QUEUE_MESSAGE_STATUS,
                status
            );
        } catch (Exception e) {
            logger.error("Failed to send message status to user {}: {}", status.userId(), e.getMessage());
        }
    }

    private WebSocketMessageStatus createMessageStatus(
        String messageId,
        String userId,
        WebSocketMessageStatus.MessageDeliveryStatus status
    ) {
        return new WebSocketMessageStatus(messageId, userId, status, Instant.now());
    }

    private record UserStatusMessage(String userId, String status) {}
} 