package com.teamconnect.api.output.teamrole;

import java.util.Set;

public record TeamRolePrivateOutput(
    String id,
    String name,
    String description
//    Set<TeamPermission> permissions
) {}
