package com.teamconnect.api.controller;

import com.teamconnect.api.input.TeamCreateInput;
import com.teamconnect.api.input.TeamUpdateInput;
import com.teamconnect.api.input.TeamMemberCreateInput;
import com.teamconnect.api.input.TeamMemberRoleUpdateInput;
import com.teamconnect.api.input.TeamMemberUpdateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.TeamOutput;
import com.teamconnect.api.output.TeamMemberOutput;
import com.teamconnect.constant.TeamConstant;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.mapper.TeamMemberMapper;
import com.teamconnect.service.TeamService;
import com.teamconnect.service.TeamMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final TeamMemberService teamMemberService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<TeamOutput>> createTeam(
            @Valid @RequestBody TeamCreateInput input) {
        TeamDto teamDto = teamService.createTeam(input);
        return ResponseWrapper.created(
                TeamMapper.INSTANCE.teamDtoToTeamOutput(teamDto),
                TeamConstant.TEAM_CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<TeamOutput>> updateTeam(
            @PathVariable String id,
            @Valid @RequestBody TeamUpdateInput input) {
        TeamDto teamDto = teamService.updateTeam(id, input);
        return ResponseWrapper.ok(
                TeamMapper.INSTANCE.teamDtoToTeamOutput(teamDto),
                TeamConstant.TEAM_UPDATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
        return ResponseWrapper.ok(null, TeamConstant.TEAM_DELETED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<TeamOutput>> getTeamById(@PathVariable String id) {
        TeamDto teamDto = teamService.getTeamById(id);
        return ResponseWrapper.ok(
                TeamMapper.INSTANCE.teamDtoToTeamOutput(teamDto));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseWrapper<TeamOutput>> getTeamByName(@PathVariable String name) {
        TeamDto teamDto = teamService.getTeamByName(name);
        return ResponseWrapper.ok(
                TeamMapper.INSTANCE.teamDtoToTeamOutput(teamDto));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<TeamOutput>>> getAllTeams() {
        List<TeamDto> teamDtos = teamService.getAllTeams();
        return ResponseWrapper.ok(
                teamDtos.stream()
                        .map(TeamMapper.INSTANCE::teamDtoToTeamOutput)
                        .toList());
    }

    @PostMapping("/members")
    public ResponseEntity<ResponseWrapper<TeamMemberOutput>> addMemberToTeam(
            @Valid @RequestBody TeamMemberCreateInput input) {
        TeamMemberDto memberDto = teamMemberService.addMember(input.getTeamId(), input.getUserId(), input.getRole());
        return ResponseWrapper.ok(
                TeamMemberMapper.INSTANCE.toOutput(memberDto),
                TeamConstant.MEMBER_ADDED);
    }

    @PutMapping("/{teamId}/members/{userId}/role")
    public ResponseEntity<ResponseWrapper<TeamMemberOutput>> updateMemberRole(
            @PathVariable String teamId,
            @PathVariable String userId,
            @Valid @RequestBody TeamMemberRoleUpdateInput input) {
        teamMemberService.updateMemberRole(teamId, userId, input.getRole());
        TeamMemberDto memberDto = teamMemberService.getMember(teamId, userId);
        return ResponseWrapper.ok(
                TeamMemberMapper.INSTANCE.toOutput(memberDto),
                TeamConstant.MEMBER_ROLE_UPDATED);
    }

    @DeleteMapping("/{teamId}/members/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> removeMemberFromTeam(
            @PathVariable String teamId,
            @PathVariable String userId) {
        teamMemberService.removeMember(teamId, userId);
        return ResponseWrapper.ok(null, TeamConstant.MEMBER_REMOVED);
    }

    @GetMapping("/member/{userId}")
    public ResponseEntity<ResponseWrapper<List<TeamOutput>>> getTeamsByMemberId(
            @PathVariable String userId) {
        List<TeamDto> teams = teamService.getTeamsByMemberId(userId);
        return ResponseWrapper.ok(
                teams.stream()
                        .map(TeamMapper.INSTANCE::teamDtoToTeamOutput)
                        .toList());
    }

    @GetMapping("/{teamId}/members")
    public ResponseEntity<ResponseWrapper<List<TeamMemberOutput>>> getTeamMembers(
            @PathVariable String teamId) {
        List<TeamMemberDto> members = teamMemberService.getTeamMembers(teamId);
        return ResponseWrapper.ok(
                members.stream()
                        .map(TeamMemberMapper.INSTANCE::toOutput)
                        .toList());
    }
}