package com.teamconnect.dto;

import com.teamconnect.common.enumarator.Availability;

public record UserProfileDto(
    String id,
    String bio,
    String language,
    String profileImageFileId,
    Availability availability,
    String statusDescription
) {} 