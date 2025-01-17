package com.teamconnect.dto;

import java.time.Instant;

public record MessageDto(
    String id,
    String authorId,
    String channelId,
    String content,
    boolean pinned,
    Instant createdAt,
    Instant updatedAt
) {}
