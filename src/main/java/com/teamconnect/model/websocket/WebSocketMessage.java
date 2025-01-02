package com.teamconnect.model.websocket;

import com.teamconnect.common.enumarator.WebSocketMessageType;
import java.time.Instant;
import java.util.Map;

public record WebSocketMessage(
    String id,
    WebSocketMessageType webSocketMessageType,
    String content,
    String senderId,
    String receiverId,
    Instant timestamp,
    Map<String, Object> metadata
) {} 