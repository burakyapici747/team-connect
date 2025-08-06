package com.teamconnect.dto;

import java.util.UUID;

public record AuthorDto(
    UUID id,
    String username,
    UUID avatarFileId,
    String avatarFileUrl
) {}
