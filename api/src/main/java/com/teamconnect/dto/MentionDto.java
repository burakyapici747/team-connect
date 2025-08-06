package com.teamconnect.dto;

import java.util.UUID;

public record MentionDto(
    UUID id,
    UUID userId,
    String userName,
    String nickname
) {}
