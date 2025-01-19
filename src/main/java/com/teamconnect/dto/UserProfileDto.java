package com.teamconnect.dto;

import com.teamconnect.common.enumarator.UserStatus;

public record UserProfileDto(
    String id,
    String bio,
    String language,
    String imageFileId,
    String imageFileUrl,
    UserStatus userStatus,
    String statusDescription
) {}
