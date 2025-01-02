package com.teamconnect.dto;

import com.teamconnect.common.enumarator.MessageTargetType;
import java.time.Instant;
import java.util.List;

public record MessageDto(
    String id,
    String senderId,
    String receiverId,
    String parentMessageId,
    MessageTargetType targetType,
    String content,
    List<ReactionDto> reactions,
    boolean deleted,
    boolean pinned,
    Instant createdAt,
    Instant updatedAt
) {
    public record ReactionDto(
        String userId,
        String reactionType
    ) {}
} 