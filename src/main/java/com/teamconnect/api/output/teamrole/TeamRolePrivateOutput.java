package com.teamconnect.api.output.teamrole;

import com.teamconnect.common.enumarator.TeamPermission;

import java.util.Set;

public record TeamRolePrivateOutput(
    String id,
    String name,
    String description,
    Set<TeamPermission> permissions
) {}
