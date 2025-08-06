package com.teamconnect.dto;

import java.util.UUID;

public record EmojiDto(
    UUID id,
    String name,
    boolean animated
) {}

