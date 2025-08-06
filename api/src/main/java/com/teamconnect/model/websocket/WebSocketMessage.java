package com.teamconnect.model.websocket;

import java.time.Instant;
import java.util.Map;

public record WebSocketMessage(
    String id,
    String content,
    String authorId,
    String channelId,
    Instant timestamp,
    Map<String, Object> metadata
) {}
