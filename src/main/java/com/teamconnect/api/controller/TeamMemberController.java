package com.teamconnect.api.controller;

import com.teamconnect.api.input.TeamMemberCreateInput;
import com.teamconnect.api.input.TeamMemberRoleAssignInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.teammember.TeamMemberPublicOutput;
import com.teamconnect.common.annotation.RequireTeamPermission;
import com.teamconnect.mapper.TeamMemberMapper;
import com.teamconnect.service.TeamMemberService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/teams/{teamId}/members")
public class TeamMemberController {
    private final TeamMemberService teamMemberService;
    private final TeamMemberMapper teamMemberMapper;

    public TeamMemberController(TeamMemberService teamMemberService, TeamMemberMapper teamMemberMapper) {
        this.teamMemberService = teamMemberService;
        this.teamMemberMapper = teamMemberMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<TeamMemberPublicOutput>>> getTeamMembers(@PathVariable String teamId) {
        return ResponseWrapper.ok(teamMemberMapper.teamMemberDtoListToTeamMemberPublicOutputList(
            teamMemberService.getTeamMembersByTeamId(teamId)
        ));
    }

    @PostMapping
    @RequireTeamPermission(value = TeamPermission.ADD_MEMBER)
    public ResponseEntity<ResponseWrapper<TeamMemberPublicOutput>> addMember(
        @PathVariable String teamId,
        @RequestBody TeamMemberCreateInput input
    ) {
        return ResponseWrapper.created(
            teamMemberMapper.teamMemberDtoToTeamMemberPublicOutput(teamMemberService.addMember(teamId, input))
        );
    }

    @DeleteMapping("/{memberId}/roles/{roleId}")
    @RequireTeamPermission(value = TeamPermission.REMOVE_ROLE)
    public ResponseEntity<ResponseWrapper<Void>> removeRoleFromMember(
        @PathVariable String teamId,
        @PathVariable String memberId,
        @PathVariable String roleId
    ) {
        teamMemberService.removeRoleFromMember(teamId, memberId, roleId);
        return ResponseWrapper.noContent();
    }

    @DeleteMapping("/{memberId}")
    @RequireTeamPermission(value = TeamPermission.REMOVE_MEMBER, isSelfUserPermission = true)
    public ResponseEntity<ResponseWrapper<Void>> removeMember(
        @PathVariable String teamId,
        @PathVariable String memberId
    ) {
        teamMemberService.removeMember(teamId, memberId);
        return ResponseWrapper.noContent();
    }

    @PostMapping("/{memberId}/roles")
    @RequireTeamPermission(value = TeamPermission.ASSIGN_ROLE)
    public ResponseEntity<ResponseWrapper<Void>> assignRoleToMember(
        @PathVariable String memberId,
        @Valid @RequestBody TeamMemberRoleAssignInput input
    ) {
        teamMemberService.assignRoleToMember(memberId, input);
        return ResponseWrapper.noContent();
    }
}
