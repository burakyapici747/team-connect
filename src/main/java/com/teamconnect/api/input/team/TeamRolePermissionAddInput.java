package com.teamconnect.api.input.team;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;

public record TeamRolePermissionAddInput(
        @NotEmpty(message = "Permissions cannot be empty") Set<TeamPermission> permissions) {
}
