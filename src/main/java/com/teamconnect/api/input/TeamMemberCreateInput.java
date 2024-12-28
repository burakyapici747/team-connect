package com.teamconnect.api.input;

import jakarta.validation.constraints.NotNull;

public record TeamMemberCreateInput(
    @NotNull(message = "User ID cannot be empty")
    String userId,

    @NotNull(message = "Team ID cannot be empty")
    String teamId
) {}
