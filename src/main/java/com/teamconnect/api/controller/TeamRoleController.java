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
import com.teamconnect.api.output.teamrole.TeamRoleOutput;
import com.teamconnect.api.output.teamrole.TeamRolePrivateOutput;
import com.teamconnect.api.output.teamrole.TeamRolePublicOutput;
import com.teamconnect.common.annotation.RequireTeamPermission;
import com.teamconnect.mapper.TeamRoleMapper;
import com.teamconnect.service.TeamRoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/teams/{teamId}/roles")
public class TeamRoleController {
//        private final TeamRoleService teamRoleService;
//
//        public TeamRoleController(TeamRoleService teamRoleService) {
//                this.teamRoleService = teamRoleService;
//        }
//
//        @GetMapping
//        @RequireTeamPermission(justTeamMember = true)
//        public ResponseEntity<ResponseWrapper<List<TeamRolePublicOutput>>> getTeamRoles(
//            @PathVariable String teamId
//        ) {
//            return ResponseWrapper.ok(
//                TeamRoleMapper.INSTANCE.teamRoleDtoListToTeamRolePublicOutputList(teamRoleService.getTeamRoles(teamId))
//			);
//        }
//
//        @GetMapping("/self")
//        @RequireTeamPermission(justTeamMember = true)
//        public ResponseEntity<ResponseWrapper<List<TeamRolePrivateOutput>>> getSpecialTeamRoles(@PathVariable String teamId) {
//			return ResponseWrapper.ok(
//				TeamRoleMapper.INSTANCE.teamRoleDtoListToTeamRolePrivateOutputList(teamRoleService.getTeamRoles(teamId))
//			);
//        }
//
//        @GetMapping("/{roleId}")
//        @RequireTeamPermission(justTeamMember = true)
//        public ResponseEntity<ResponseWrapper<TeamRoleOutput>> getTeamRoleById(
//			@PathVariable String teamId,
//			@PathVariable String roleId
//		) {
//			return ResponseWrapper.ok(
//				TeamRoleMapper.INSTANCE.teamRoleDtoToTeamRoleOutput(
//					teamRoleService.getTeamRoleById(teamId, roleId))
//			);
//        }
//
//        @GetMapping("/{roleId}/permissions")
//        @RequireTeamPermission(justTeamMember = true)
//        public ResponseEntity<ResponseWrapper<Set<TeamPermission>>> getRolePermissions(
//            @PathVariable String teamId,
//			@PathVariable String roleId
//		) {
//			return ResponseWrapper.ok(teamRoleService.getRolePermissions(teamId, roleId));
//        }
//
//        @PostMapping
//        @RequireTeamPermission(value = TeamPermission.CREATE_ROLE)
//        public ResponseEntity<ResponseWrapper<TeamRoleOutput>> createTeamRole(
//			@PathVariable String teamId,
//			@Valid @RequestBody TeamRoleCreateInput input
//        ) {
//			return ResponseWrapper.created(
//				TeamRoleMapper.INSTANCE.teamRoleDtoToTeamRoleOutput(teamRoleService.createTeamRole(teamId, input))
//			);
//        }
//
//        @PutMapping("/{roleId}")
//		@RequireTeamPermission(value = TeamPermission.UPDATE_ROLE)
//        public ResponseEntity<ResponseWrapper<TeamRoleOutput>> updateTeamRole(
//			@PathVariable String teamId,
//			@PathVariable String roleId,
//			@Valid @RequestBody TeamRoleUpdateInput input
//		) {
//			return ResponseWrapper.ok(
//				TeamRoleMapper.INSTANCE.teamRoleDtoToTeamRoleOutput(
//					teamRoleService.updateTeamRole(teamId, roleId, input)
//				)
//			);
//        }
//
//        @DeleteMapping("/{roleId}")
//		@RequireTeamPermission(value = TeamPermission.REMOVE_ROLE)
//        public ResponseEntity<ResponseWrapper<Void>> deleteTeamRole(
//            @PathVariable String teamId,
//			@PathVariable String roleId
//		) {
//			teamRoleService.deleteTeamRole(teamId, roleId);
//			return ResponseWrapper.noContent();
//        }
//
//        @PutMapping("/{roleId}/permissions")
//		@RequireTeamPermission(value = TeamPermission.UPDATE_ROLE)
//        public ResponseEntity<ResponseWrapper<TeamRoleOutput>> updateRolePermissions(
//			@PathVariable String teamId,
//			@PathVariable String roleId,
//			@Valid @RequestBody TeamRolePermissionUpdateInput input,
//			@AuthenticationPrincipal UserDetails userDetails
//		) {
//			return ResponseWrapper.ok(
//				TeamRoleMapper.INSTANCE.teamRoleDtoToTeamRoleOutput(
//					teamRoleService.updateRolePermissions(teamId, roleId, input)
//				)
//			);
//        }
//
//        @PostMapping("/{roleId}/permissions")
//        @RequireTeamPermission(value = TeamPermission.ASSIGN_ROLE)
//        public ResponseEntity<ResponseWrapper<Void>> addPermissionsToRole(
//			@PathVariable String teamId,
//			@PathVariable String roleId,
//			@Valid @RequestBody TeamRolePermissionAddInput input,
//			@AuthenticationPrincipal UserDetails userDetails
//		) {
//			teamRoleService.addPermissionsToRole(teamId, roleId, input.permissions());
//			return ResponseWrapper.noContent();
//        }
//
//        @DeleteMapping("/{roleId}/permissions/{permission}")
//		@RequireTeamPermission(value = TeamPermission.UPDATE_ROLE)
//        public ResponseEntity<ResponseWrapper<Void>> removePermissionFromRole(
//			@PathVariable String teamId,
//			@PathVariable String roleId,
//			@PathVariable TeamPermission permission,
//			@AuthenticationPrincipal UserDetails userDetails
//		) {
//			teamRoleService.removePermissionFromRole(teamId, roleId, permission, userDetails.getUsername());
//			return ResponseWrapper.noContent();
//        }
}
