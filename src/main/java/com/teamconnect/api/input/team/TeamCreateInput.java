package com.teamconnect.api.input.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeamCreateInput(
        @NotBlank(message = "Team name is required")
        @Size(min = 3, max = 50, message = "Team name must be between 3 and 50 characters")
        String name
) {}
