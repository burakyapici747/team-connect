package com.teamconnect.api.controller;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.team.TeamCreateOutput;
import com.teamconnect.api.output.team.TeamPublicDetailsOutput;
import com.teamconnect.api.output.teammember.TeamPrivateOutput;
import com.teamconnect.common.annotation.RequireTeamPermission;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api/teams")
public class TeamController {
	private final TeamService teamService;

	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseWrapper<TeamPublicDetailsOutput>> getTeamById(@PathVariable String id) {
		return ResponseWrapper.ok(TeamMapper.INSTANCE.teamDtoToTeamPublicDetailsOutput(teamService.getTeamById(id)));
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<TeamCreateOutput>> createTeam(
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestPart("input") @Valid TeamCreateInput input,
        @RequestPart("file") MultipartFile file
    ) throws IOException {
		return ResponseWrapper.created(
            TeamMapper.INSTANCE.teamDtoToTeamCreateOutput(
                teamService.createTeam(userDetails.getUsername(), input, file)
            )
        );
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseWrapper<TeamPrivateOutput>> updateTeam(
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
