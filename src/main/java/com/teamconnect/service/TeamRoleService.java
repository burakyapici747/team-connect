package com.teamconnect.service;

import java.util.List;
import java.util.Set;

import com.teamconnect.api.input.team.TeamRoleCreateInput;
import com.teamconnect.api.input.team.TeamRolePermissionUpdateInput;
import com.teamconnect.api.input.team.TeamRoleUpdateInput;
import com.teamconnect.dto.TeamRoleDto;

public interface TeamRoleService {
    TeamRoleDto createTeamRole(String teamId, TeamRoleCreateInput input);

    TeamRoleDto updateTeamRole(String teamId, String roleId, TeamRoleUpdateInput input);

    void deleteTeamRole(String teamId, String roleId);

    TeamRoleDto getTeamRoleById(String teamId, String roleId);

    List<TeamRoleDto> getTeamRoles(String teamId);

    TeamRoleDto updateRolePermissions(String teamId, String roleId, TeamRolePermissionUpdateInput input);

    Set<TeamPermission> getRolePermissions(String teamId, String roleId);

    void addPermissionsToRole(String teamId, String roleId, Set<TeamPermission> permissions);

    void removePermissionFromRole(String teamId, String roleId, TeamPermission permission, String email);

    TeamRole findTeamRoleById(String roleId);
}
