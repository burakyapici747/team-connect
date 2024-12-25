package com.teamconnect.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamconnect.api.input.team.TeamRoleCreateInput;
import com.teamconnect.api.input.team.TeamRolePermissionUpdateInput;
import com.teamconnect.api.input.team.TeamRoleUpdateInput;
import com.teamconnect.common.enumarator.TeamMemberType;
import com.teamconnect.common.enumarator.TeamPermission;
import com.teamconnect.dto.TeamRoleDto;
import com.teamconnect.exception.UnauthorizedAccessException;
import com.teamconnect.mapper.TeamRoleMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.TeamRole;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.TeamMemberRepository;
import com.teamconnect.repository.TeamRoleRepository;
import com.teamconnect.service.TeamRoleService;
import com.teamconnect.service.TeamService;
import com.teamconnect.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamRoleServiceImpl implements TeamRoleService {
    private final TeamRoleRepository teamRoleRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final TeamRoleMapper teamRoleMapper;

    @Override
    public TeamRoleDto createTeamRole(String teamId, TeamRoleCreateInput input, String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamRole teamRole = createAndSaveTeamRole(input);
        team.getTeamRoles().add(teamRole);

        return teamRoleMapper.teamRoleToTeamRoleDto(teamRole);
    }

    @Override
    public TeamRoleDto updateTeamRole(String teamId, String roleId, TeamRoleUpdateInput input, String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        teamRoleMapper.updateTeamRoleFromUpdateInput(input, teamRole);
        return teamRoleMapper.teamRoleToTeamRoleDto(teamRoleRepository.save(teamRole));
    }

    @Override
    public void deleteTeamRole(String teamId, String roleId, String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        team.getTeamRoles().remove(teamRole);
        teamRoleRepository.delete(teamRole);
    }

    @Override
    public TeamRoleDto getTeamRoleById(String teamId, String roleId) {
        Team team = teamService.getTeamEntityById(teamId);
        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);
        return teamRoleMapper.teamRoleToTeamRoleDto(teamRole);
    }

    @Override
    public List<TeamRoleDto> getTeamRoles(String teamId) {
        Team team = teamService.getTeamEntityById(teamId);
        return team.getTeamRoles().stream()
                .map(teamRoleMapper::teamRoleToTeamRoleDto)
                .toList();
    }

    @Override
    public void assignRoleToMember(String teamId, String memberId, String roleId, String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamMember member = findTeamMemberById(memberId);
        TeamRole role = findTeamRoleById(roleId);

        validateMemberBelongsToTeam(team, member);
        validateRoleBelongsToTeam(team, role);

        addRoleToMember(member, role);
    }

    @Override
    public void removeRoleFromMember(String teamId, String memberId, String roleId, String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamMember member = findTeamMemberById(memberId);
        TeamRole role = findTeamRoleById(roleId);

        validateMemberBelongsToTeam(team, member);
        validateRoleBelongsToTeam(team, role);

        removeRoleFromMember(member, role);
    }

    @Override
    public TeamRoleDto updateRolePermissions(String teamId, String roleId, TeamRolePermissionUpdateInput input,
            String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        teamRole.setPermissions(input.permissions());
        return teamRoleMapper.teamRoleToTeamRoleDto(teamRoleRepository.save(teamRole));
    }

    @Override
    public Set<TeamPermission> getRolePermissions(String teamId, String roleId) {
        Team team = teamService.getTeamEntityById(teamId);
        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);
        return teamRole.getPermissions() != null ? teamRole.getPermissions() : new HashSet<>();
    }

    @Override
    public void addPermissionsToRole(String teamId, String roleId, Set<TeamPermission> permissions, String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        addPermissionsToRole(teamRole, permissions);
    }

    @Override
    public void removePermissionFromRole(String teamId, String roleId, TeamPermission permission, String email) {
        Team team = teamService.getTeamEntityById(teamId);
        validateCreatorAccess(team, email);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        removePermissionFromRole(teamRole, permission);
    }

    // Private helper methods for entity operations
    private TeamRole createAndSaveTeamRole(TeamRoleCreateInput input) {
        TeamRole teamRole = teamRoleMapper.teamRoleCreateInputToTeamRole(input);
        return teamRoleRepository.save(teamRole);
    }

    private void addRoleToMember(TeamMember member, TeamRole role) {
        member.getTeamRoles().add(role);
        teamMemberRepository.save(member);
    }

    private void removeRoleFromMember(TeamMember member, TeamRole role) {
        member.getTeamRoles().remove(role);
        teamMemberRepository.save(member);
    }

    private void addPermissionsToRole(TeamRole teamRole, Set<TeamPermission> permissions) {
        if (teamRole.getPermissions() == null) {
            teamRole.setPermissions(new HashSet<>());
        }
        teamRole.getPermissions().addAll(permissions);
        teamRoleRepository.save(teamRole);
    }

    private void removePermissionFromRole(TeamRole teamRole, TeamPermission permission) {
        if (teamRole.getPermissions() != null) {
            teamRole.getPermissions().remove(permission);
            teamRoleRepository.save(teamRole);
        }
    }

    // Private helper methods for validation
    private TeamRole findTeamRoleById(String roleId) {
        return teamRoleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Team role not found"));
    }

    private TeamMember findTeamMemberById(String memberId) {
        return teamMemberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Team member not found"));
    }

    private void validateCreatorAccess(Team team, String email) {
        User currentUser = userService.getUserEntityByEmail(email);
        if (!isUserTeamCreator(team, currentUser)) {
            throw new UnauthorizedAccessException("Only team creator can manage roles");
        }
    }

    private boolean isUserTeamCreator(Team team, User user) {
        return team.getTeamMembers().stream()
                .anyMatch(member -> member.getUser().getId().equals(user.getId())
                        && member.getMemberType() == TeamMemberType.CREATOR);
    }

    private void validateMemberBelongsToTeam(Team team, TeamMember member) {
        if (!isMemberInTeam(team, member)) {
            throw new EntityNotFoundException("Member does not belong to this team");
        }
    }

    private boolean isMemberInTeam(Team team, TeamMember member) {
        return team.getTeamMembers().stream()
                .anyMatch(m -> m.getId().equals(member.getId()));
    }

    private void validateRoleBelongsToTeam(Team team, TeamRole role) {
        if (!isRoleInTeam(team, role)) {
            throw new EntityNotFoundException("Role does not belong to this team");
        }
    }

    private boolean isRoleInTeam(Team team, TeamRole role) {
        return team.getTeamRoles().stream()
                .anyMatch(r -> r.getId().equals(role.getId()));
    }
}