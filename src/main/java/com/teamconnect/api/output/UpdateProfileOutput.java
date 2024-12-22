package com.teamconnect.api.output;

public record UpdateProfileOutput(
    String id,
    String bio,
    String profileImageFileId,
    String language
) {}
