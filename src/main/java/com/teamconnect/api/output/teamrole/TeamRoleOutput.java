package com.teamconnect.api.output.teamrole;

import java.time.Instant;
import java.util.Set;

import com.teamconnect.common.enumarator.TeamPermission;

public record TeamRoleOutput(
    String id,
    String name,
    String description,
    Set<TeamPermission> permissions,
    boolean isDefault,
    Instant createdAt,
    Instant updatedAt
) { }
