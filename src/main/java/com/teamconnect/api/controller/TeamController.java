package com.teamconnect.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.team.TeamCreateOutput;
import com.teamconnect.api.output.team.TeamMemberDetailsPublicOutput;
import com.teamconnect.api.output.team.TeamPublicDetailsOutput;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/teams")
@RequiredArgsConstructor
public class TeamController {
	private final TeamService teamService;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseWrapper<TeamPublicDetailsOutput>> getTeamById(@PathVariable String id) {
		return ResponseWrapper.ok(
				TeamMapper.INSTANCE.teamDtoToTeamPublicDetailsOutput(
						teamService.getTeamById(id)));
	}

	@GetMapping("/{teamId}/members")
	public ResponseEntity<ResponseWrapper<List<TeamMemberDetailsPublicOutput>>> getTeamMembers(
			@PathVariable String teamId) {
		return ResponseWrapper.ok(
				TeamMapper.INSTANCE.teamMemberDtosToTeamMemberDetailsPublicOutputs(
						teamService.getTeamMembersByTeamId(teamId)));
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<TeamCreateOutput>> createTeam(
			@AuthenticationPrincipal UserDetails userDetails,
			@RequestBody TeamCreateInput teamCreateInput) {
		return ResponseWrapper.ok(
				TeamMapper.INSTANCE.teamDtoToTeamCreateOutput(
						teamService.createTeam(userDetails.getUsername(), teamCreateInput)));
	}
}
