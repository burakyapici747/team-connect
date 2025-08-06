package com.teamconnect.dto;

import java.util.UUID;

public record MessageApplicationDto(
    UUID id,
    String name,
    String description,
    String icon,
    String coverImage
) {}

