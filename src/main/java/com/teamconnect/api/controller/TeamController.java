package com.teamconnect.api.controller;

import java.util.List;

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

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.team.TeamCreateOutput;
import com.teamconnect.api.output.team.TeamMemberDetailsPublicOutput;
import com.teamconnect.api.output.team.TeamPrivateDetailsOutput;
import com.teamconnect.api.output.team.TeamPublicDetailsOutput;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/teams")
public class TeamController {
	private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

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
			@Valid @RequestBody TeamCreateInput input
    ) {
        return ResponseWrapper.created(
            TeamMapper.INSTANCE.teamDtoToTeamCreateOutput(
                teamService.createTeam(userDetails.getUsername(), input)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseWrapper<TeamPrivateDetailsOutput>> updateTeam(
			@PathVariable String id,
			@Valid @RequestBody TeamUpdateInput input,
			@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseWrapper.ok(
				TeamMapper.INSTANCE.teamDtoToTeamPrivateDetailsOutput(
						teamService.updateTeam(id, input, userDetails.getUsername())));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseWrapper<Void>> deleteTeam(
			@PathVariable String id,
			@Valid @RequestBody TeamDeleteInput input,
			@AuthenticationPrincipal UserDetails userDetails) {
		teamService.deleteTeam(id, input, userDetails.getUsername());
		return ResponseWrapper.noContent();
	}

	@GetMapping("/my-teams")
	public ResponseEntity<ResponseWrapper<List<TeamPrivateDetailsOutput>>> getUserTeams(
			@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseWrapper.ok(
				teamService.getUserTeams(userDetails.getUsername()).stream()
						.map(TeamMapper.INSTANCE::teamDtoToTeamPrivateDetailsOutput)
						.toList());
	}
}
