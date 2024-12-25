package com.teamconnect.dto;

import java.time.Instant;

public record TeamDto(
    String id,
    String name,
    String description,
    Instant createdAt,
    Instant updatedAt
){}
