package com.teamconnect.service;

import java.util.List;
import java.util.Set;

import com.teamconnect.api.input.team.TeamRoleCreateInput;
import com.teamconnect.api.input.team.TeamRolePermissionUpdateInput;
import com.teamconnect.api.input.team.TeamRoleUpdateInput;
import com.teamconnect.common.enumarator.TeamPermission;
import com.teamconnect.dto.TeamRoleDto;

public interface TeamRoleService {
    TeamRoleDto createTeamRole(String teamId, TeamRoleCreateInput input, String email);

    TeamRoleDto updateTeamRole(String teamId, String roleId, TeamRoleUpdateInput input, String email);

    void deleteTeamRole(String teamId, String roleId, String email);

    TeamRoleDto getTeamRoleById(String teamId, String roleId);

    List<TeamRoleDto> getTeamRoles(String teamId);

    void assignRoleToMember(String teamId, String memberId, String roleId, String email);

    void removeRoleFromMember(String teamId, String memberId, String roleId, String email);

    // Permission yönetimi metodları
    TeamRoleDto updateRolePermissions(String teamId, String roleId, TeamRolePermissionUpdateInput input, String email);

    Set<TeamPermission> getRolePermissions(String teamId, String roleId);

    void addPermissionsToRole(String teamId, String roleId, Set<TeamPermission> permissions, String email);

    void removePermissionFromRole(String teamId, String roleId, TeamPermission permission, String email);
}