package com.teamconnect.dto;

public record ReactionDto(
    int count,
    EmojiDto emoji,
    boolean me
) {}
