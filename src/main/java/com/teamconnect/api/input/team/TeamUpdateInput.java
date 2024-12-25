package com.teamconnect.api.input.team;

import jakarta.validation.constraints.Size;

public record TeamUpdateInput(
        @Size(min = 3, max = 50, message = "Team name must be between 3 and 50 characters") String name,

        @Size(max = 500, message = "Description cannot exceed 500 characters") String description) {
}