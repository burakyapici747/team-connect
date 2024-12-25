package com.teamconnect.api.output.user;

public record UpdateProfileOutput(
    String id,
    String bio,
    String profileImageFileId,
    String language
) {}
