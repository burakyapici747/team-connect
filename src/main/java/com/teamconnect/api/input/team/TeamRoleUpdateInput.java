package com.teamconnect.api.input.team;

import java.util.Set;

import jakarta.validation.constraints.Size;

public record TeamRoleUpdateInput(
        @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
        String name,

        @Size(max = 200, message = "Description cannot exceed 200 characters")
        String description,

        Set<TeamPermission> permissions,

        Boolean isDefault
) {}
