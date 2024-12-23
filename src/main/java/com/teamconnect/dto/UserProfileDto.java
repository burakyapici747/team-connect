package com.teamconnect.dto;

import com.teamconnect.common.enumarator.Availability;

public record UserProfileDto(
    String id,
    String bio,
    String profileImageFileId,
    String language,
    Availability availability,
    String statusDescription
) {} 