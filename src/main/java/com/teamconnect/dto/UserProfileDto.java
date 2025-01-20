package com.teamconnect.dto;

public record UserProfileDto(
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
