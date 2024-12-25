package com.teamconnect.api.output.user;

import com.teamconnect.common.enumarator.Availability;

public record UserProfilePublicOutput(
    String id,
    String bio,
    String language,
    String profileImageFileId,
    Availability availability,
    String statusDescription
) {}
