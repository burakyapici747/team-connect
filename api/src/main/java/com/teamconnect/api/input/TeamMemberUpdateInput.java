package com.teamconnect.api.input;

import jakarta.validation.constraints.NotNull;

public record TeamMemberUpdateInput(
    @NotNull(message = "Role cannot be empty")
    String role
) {} 