package com.teamconnect.api.input.team;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public record TeamRolePermissionUpdateInput(
        @NotNull(message = "Permissions cannot be null") Set<TeamPermission> permissions) {
}
