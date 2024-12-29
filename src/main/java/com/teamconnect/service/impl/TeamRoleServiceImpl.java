package com.teamconnect.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamconnect.api.input.team.TeamRoleCreateInput;
import com.teamconnect.api.input.team.TeamRolePermissionUpdateInput;
import com.teamconnect.api.input.team.TeamRoleUpdateInput;
import com.teamconnect.common.enumarator.TeamPermission;
import com.teamconnect.dto.TeamRoleDto;
import com.teamconnect.mapper.TeamRoleMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamRole;
import com.teamconnect.repository.TeamRoleRepository;
import com.teamconnect.service.TeamRoleService;
import com.teamconnect.service.TeamService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class TeamRoleServiceImpl implements TeamRoleService {
    private final TeamRoleRepository teamRoleRepository;
    private final TeamService teamService;

    public TeamRoleServiceImpl(
        TeamRoleRepository teamRoleRepository,
        TeamService teamService
    ) {
        this.teamRoleRepository = teamRoleRepository;
        this.teamService = teamService;
    }

    @Override
    public List<TeamRoleDto> getTeamRoles(String teamId) {
        Team team = teamService.getTeamEntityById(teamId);
        return team.getTeamRoles().stream()
            .map(TeamRoleMapper.INSTANCE::teamRoleToTeamRoleDto)
            .toList();
    }

    @Override
    public TeamRoleDto createTeamRole(String teamId, TeamRoleCreateInput input) {
        Team team = teamService.getTeamEntityById(teamId);

        TeamRole teamRole = TeamRoleMapper.INSTANCE.teamRoleCreateInputToTeamRole(input);

        team.getTeamRoles().add(teamRole);

        teamRoleRepository.save(teamRole);

        return TeamRoleMapper.INSTANCE.teamRoleToTeamRoleDto(teamRole);
    }

    @Override
    public TeamRoleDto updateTeamRole(String teamId, String roleId, TeamRoleUpdateInput input) {
        Team team = teamService.getTeamEntityById(teamId);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        teamRoleRepository.save(teamRole);

        TeamRoleMapper.INSTANCE.updateTeamRoleFromUpdateInput(input, teamRole);
        return TeamRoleMapper.INSTANCE.teamRoleToTeamRoleDto(teamRoleRepository.save(teamRole));
    }

    @Override
    public void deleteTeamRole(String teamId, String roleId) {
        Team team = teamService.getTeamEntityById(teamId);

        TeamRole teamRole = findTeamRoleById(roleId);

        validateRoleBelongsToTeam(team, teamRole);

        team.getTeamRoles().remove(teamRole);
        teamRoleRepository.delete(teamRole);
    }

    @Override
    public TeamRoleDto getTeamRoleById(String teamId, String roleId) {
        TeamRole teamRole = findTeamRoleByRoleIdAndTeamId(roleId, teamId);
        return TeamRoleMapper.INSTANCE.teamRoleToTeamRoleDto(teamRole);
    }

    @Override
    public TeamRoleDto updateRolePermissions(String teamId, String roleId, TeamRolePermissionUpdateInput input) {
        Team team = teamService.getTeamEntityById(teamId);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        teamRole.setPermissions(input.permissions());

        return TeamRoleMapper.INSTANCE.teamRoleToTeamRoleDto(teamRoleRepository.save(teamRole));
    }

    @Override
    public Set<TeamPermission> getRolePermissions(String teamId, String roleId) {
        Team team = teamService.getTeamEntityById(teamId);
        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);
        return teamRole.getPermissions() != null ? teamRole.getPermissions() : new HashSet<>();
    }

    @Override
    public void addPermissionsToRole(String teamId, String roleId, Set<TeamPermission> permissions) {
        Team team = teamService.getTeamEntityById(teamId);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        addPermissionsToRole(teamRole, permissions);
    }

    @Override
    public void removePermissionFromRole(String teamId, String roleId, TeamPermission permission, String email) {
        Team team = teamService.getTeamEntityById(teamId);

        TeamRole teamRole = findTeamRoleById(roleId);
        validateRoleBelongsToTeam(team, teamRole);

        teamRole.getPermissions().remove(permission);
        teamRoleRepository.save(teamRole);
    }

    private void addPermissionsToRole(TeamRole teamRole, Set<TeamPermission> permissions) {
        if (teamRole.getPermissions() == null) {
            teamRole.setPermissions(new HashSet<>());
        }
        teamRole.getPermissions().addAll(permissions);
        teamRoleRepository.save(teamRole);
    }

    @Override
    public TeamRole findTeamRoleById(String roleId) {
        return teamRoleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Team role not found"));
    }

    private TeamRole findTeamRoleByRoleIdAndTeamId(String roleId, String teamId) {
        Team team = teamService.getTeamEntityById(teamId);
        return team.getTeamRoles().stream().filter(role -> role.getId().equals(roleId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Team role not found"));
    }

    private void validateRoleBelongsToTeam(Team team, TeamRole role) {
        if (!team.getTeamRoles().contains(role)) {
            throw new EntityNotFoundException("Team role not found");
        }
    }
}
