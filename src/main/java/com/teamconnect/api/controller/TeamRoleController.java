package com.teamconnect.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamconnect.api.input.team.TeamRoleCreateInput;
import com.teamconnect.api.input.team.TeamRolePermissionAddInput;
import com.teamconnect.api.input.team.TeamRolePermissionUpdateInput;
import com.teamconnect.api.input.team.TeamRoleUpdateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.team.TeamRoleOutput;
import com.teamconnect.common.enumarator.TeamPermission;
import com.teamconnect.mapper.TeamRoleMapper;
import com.teamconnect.service.TeamRoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/teams/{teamId}/roles")
@RequiredArgsConstructor
public class TeamRoleController {
    private final TeamRoleService teamRoleService;
    private final TeamRoleMapper teamRoleMapper;

    @PostMapping
    public ResponseEntity<ResponseWrapper<TeamRoleOutput>> createTeamRole(
            @PathVariable String teamId,
            @Valid @RequestBody TeamRoleCreateInput input,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.created(
                teamRoleMapper.teamRoleDtoToTeamRoleOutput(
                        teamRoleService.createTeamRole(teamId, input, userDetails.getUsername())));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ResponseWrapper<TeamRoleOutput>> updateTeamRole(
            @PathVariable String teamId,
            @PathVariable String roleId,
            @Valid @RequestBody TeamRoleUpdateInput input,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                teamRoleMapper.teamRoleDtoToTeamRoleOutput(
                        teamRoleService.updateTeamRole(teamId, roleId, input, userDetails.getUsername())));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteTeamRole(
            @PathVariable String teamId,
            @PathVariable String roleId,
            @AuthenticationPrincipal UserDetails userDetails) {
        teamRoleService.deleteTeamRole(teamId, roleId, userDetails.getUsername());
        return ResponseWrapper.noContent();
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ResponseWrapper<TeamRoleOutput>> getTeamRoleById(
            @PathVariable String teamId,
            @PathVariable String roleId) {
        return ResponseWrapper.ok(
                teamRoleMapper.teamRoleDtoToTeamRoleOutput(
                        teamRoleService.getTeamRoleById(teamId, roleId)));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<TeamRoleOutput>>> getTeamRoles(
            @PathVariable String teamId) {
        return ResponseWrapper.ok(
                teamRoleService.getTeamRoles(teamId).stream()
                        .map(teamRoleMapper::teamRoleDtoToTeamRoleOutput)
                        .toList());
    }

    @PostMapping("/{roleId}/members/{memberId}")
    public ResponseEntity<ResponseWrapper<Void>> assignRoleToMember(
            @PathVariable String teamId,
            @PathVariable String roleId,
            @PathVariable String memberId,
            @AuthenticationPrincipal UserDetails userDetails) {
        teamRoleService.assignRoleToMember(teamId, memberId, roleId, userDetails.getUsername());
        return ResponseWrapper.noContent();
    }

    @DeleteMapping("/{roleId}/members/{memberId}")
    public ResponseEntity<ResponseWrapper<Void>> removeRoleFromMember(
            @PathVariable String teamId,
            @PathVariable String roleId,
            @PathVariable String memberId,
            @AuthenticationPrincipal UserDetails userDetails) {
        teamRoleService.removeRoleFromMember(teamId, memberId, roleId, userDetails.getUsername());
        return ResponseWrapper.noContent();
    }

    @PutMapping("/{roleId}/permissions")
    public ResponseEntity<ResponseWrapper<TeamRoleOutput>> updateRolePermissions(
            @PathVariable String teamId,
            @PathVariable String roleId,
            @Valid @RequestBody TeamRolePermissionUpdateInput input,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                teamRoleMapper.teamRoleDtoToTeamRoleOutput(
                        teamRoleService.updateRolePermissions(teamId, roleId, input, userDetails.getUsername())));
    }

    @GetMapping("/{roleId}/permissions")
    public ResponseEntity<ResponseWrapper<Set<TeamPermission>>> getRolePermissions(
            @PathVariable String teamId,
            @PathVariable String roleId) {
        return ResponseWrapper.ok(teamRoleService.getRolePermissions(teamId, roleId));
    }

    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<ResponseWrapper<Void>> addPermissionsToRole(
            @PathVariable String teamId,
            @PathVariable String roleId,
            @Valid @RequestBody TeamRolePermissionAddInput input,
            @AuthenticationPrincipal UserDetails userDetails) {
        teamRoleService.addPermissionsToRole(teamId, roleId, input.permissions(), userDetails.getUsername());
        return ResponseWrapper.noContent();
    }

    @DeleteMapping("/{roleId}/permissions/{permission}")
    public ResponseEntity<ResponseWrapper<Void>> removePermissionFromRole(
            @PathVariable String teamId,
            @PathVariable String roleId,
            @PathVariable TeamPermission permission,
            @AuthenticationPrincipal UserDetails userDetails) {
        teamRoleService.removePermissionFromRole(teamId, roleId, permission, userDetails.getUsername());
        return ResponseWrapper.noContent();
    }
}