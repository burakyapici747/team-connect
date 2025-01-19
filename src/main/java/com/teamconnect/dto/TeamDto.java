package com.teamconnect.dto;

import java.time.Instant;

public record TeamDto(
    String id,
    String name,
    String description,
    String icon,
    Instant createdAt,
    Instant updatedAt
){}
