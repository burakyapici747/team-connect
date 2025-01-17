package com.teamconnect.api.output;

import java.time.Instant;

public record MessageOutput(
    String id,
    String authorId,
    String receiverId,
    String parentMessageId,
    String content,
    boolean pinned,
    Instant createdAt,
    Instant updatedAt
) {}
