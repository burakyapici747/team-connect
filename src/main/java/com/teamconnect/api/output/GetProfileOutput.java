package com.teamconnect.api.output;

import com.teamconnect.common.enumarator.Availability;

public record GetProfileOutput(
    String id,
    String bio,
    String profileImageFileId,
    String language,
    Availability availability,
    String statusDescription
) {} 