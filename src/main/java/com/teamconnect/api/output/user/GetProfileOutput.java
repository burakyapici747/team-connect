package com.teamconnect.api.output.user;

import com.teamconnect.common.enumarator.UserStatus;

public record GetProfileOutput(
    String id,
    String bio,
    String profileImageFileId,
    String language,
    UserStatus userStatus,
    String statusDescription
) {}
