package com.teamconnect.api.output;

import com.teamconnect.common.enumarator.MessageTargetType;
import java.time.Instant;
import java.util.List;

public record MessageOutput(
    String id,
    String senderId,
    String receiverId,
    String parentMessageId,
    MessageTargetType targetType,
    String content,
    List<ReactionOutput> reactions,
    boolean deleted,
    boolean pinned,
    Instant createdAt,
    Instant updatedAt
) {
    public record ReactionOutput(
        String userId,
        String reactionType
    ) {}
} 