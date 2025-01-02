package com.teamconnect.service;

import com.teamconnect.model.websocket.WebSocketMessage;
import com.teamconnect.model.websocket.WebSocketMessageStatus;

public interface WebSocketMessageService {
    void sendPrivateMessage(WebSocketMessage message, String senderId);
    void updateMessageStatus(WebSocketMessageStatus status, String userId);
    void sendMessageStatus(String messageId, String userId, WebSocketMessageStatus.MessageDeliveryStatus status);
    void handleOfflineMessages(String userId);
    void handleUserStatusChange(String userId, boolean isOnline);
} 