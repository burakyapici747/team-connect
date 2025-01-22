package com.teamconnect.api.output.user;

public record AuthorOutput(
    String id,
    String username,
    String avatarFileId,
    String avatarFileUrl
) {}
