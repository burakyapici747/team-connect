package com.teamconnect.api.output.user;

import com.teamconnect.common.enumarator.UserStatus;

public record UserProfilePrivateOutput(
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
