package com.teamconnect.service;

import java.util.List;

import com.teamconnect.common.enumarator.TeamPermission;

public interface PermissionService {
    boolean hasTeamPermission(String teamId, String userId, TeamPermission permission);

    boolean isTeamCreator(String teamId, String userId);

    void validateTeamPermission(String teamId, String userId, List<TeamPermission> teamPermissions,
            boolean allTeamPermissionRequired, boolean isSelfUserPermission, boolean justTeamMember);
}