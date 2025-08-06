package com.teamconnect.dto;

import java.util.UUID;

public record StickerDto(
    UUID id,
    String name,
    String description,
    String tags,
    Integer type,
    Integer formatType
) {}
