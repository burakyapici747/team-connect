package com.teamconnect.api.output.user;

public record UserProfilePublicOutput(
    String id,
    String avatarFileId,
    String avatarFileUrl,
    String fullName,
    String bio,
    String timezone,
    String language,
    String birthday,
    String gender,
    String themePreference
) {}
