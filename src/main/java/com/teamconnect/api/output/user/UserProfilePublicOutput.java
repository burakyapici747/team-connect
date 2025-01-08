package com.teamconnect.api.output.user;

import com.teamconnect.common.enumarator.UserStatus;

public record UserProfilePublicOutput(
    String id,
    String bio,
    String language,
    String profileImageFileId,
    UserStatus userStatus,
    String statusDescription
) {}
