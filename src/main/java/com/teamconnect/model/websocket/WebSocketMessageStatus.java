package com.teamconnect.model.websocket;

import java.time.Instant;

public record WebSocketMessageStatus(
    String messageId,
    String userId,
    MessageDeliveryStatus status,
    Instant timestamp
) {
    public enum MessageDeliveryStatus {
        SENT,
        DELIVERED,
        READ,
        PENDING
    }
} 