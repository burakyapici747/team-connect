package com.teamconnect.api.output.teamrole;

import java.time.Instant;
import java.util.Set;

public record TeamRoleOutput(
    String id,
    String name,
    String description,
    Set<TeamPermission> permissions,
    boolean isDefault,
    Instant createdAt,
    Instant updatedAt
) { }
