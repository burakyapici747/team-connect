package com.teamconnect.api.controller;

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
import com.teamconnect.api.output.team.TeamPrivateDetailsOutput;
import com.teamconnect.api.output.team.TeamPublicDetailsOutput;
import com.teamconnect.common.annotation.RequireTeamPermission;
import com.teamconnect.common.enumarator.TeamPermission;
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
				TeamMapper.INSTANCE.teamDtoToTeamPublicDetailsOutput(teamService.getTeamById(id)));
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<TeamCreateOutput>> createTeam(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody TeamCreateInput input
    ) {
		return ResponseWrapper.created(
            TeamMapper.INSTANCE.teamDtoToTeamCreateOutput(
                teamService.createTeam(userDetails.getUsername(), input)
            )
        );
	}

	@PutMapping("/{id}")
    @RequireTeamPermission(TeamPermission.UPDATE_TEAM)
	public ResponseEntity<ResponseWrapper<TeamPrivateDetailsOutput>> updateTeam(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable String id,
        @Valid @RequestBody TeamUpdateInput input
    ) {
		return ResponseWrapper.ok(
            TeamMapper.INSTANCE.teamDtoToTeamPrivateDetailsOutput(teamService.updateTeam(id, input))
        );
	}

	@DeleteMapping("/{id}")
    @RequireTeamPermission
	public ResponseEntity<ResponseWrapper<Void>> deleteTeam(
        @PathVariable String id,
        @Valid @RequestBody TeamDeleteInput input
    ) {
		teamService.deleteTeam(id, input);
		return ResponseWrapper.noContent();
	}
}
