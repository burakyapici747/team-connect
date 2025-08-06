package com.teamconnect.dto;

import java.util.UUID;

public record InteractionDto(
    UUID id,
    Integer type,
    String name,
    AuthorDto user
) {}
