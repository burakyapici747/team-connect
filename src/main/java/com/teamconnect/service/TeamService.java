package com.teamconnect.service;

import java.util.List;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.model.sql.Team;

public interface TeamService {
    TeamDto getTeamById(String id);

    TeamDto createTeam(String userEmail, TeamCreateInput teamCreateInput);

    TeamDto updateTeam(String id, TeamUpdateInput input);

    void deleteTeam(String id, TeamDeleteInput input);

    List<TeamDto> getTeamsByUserId(String userId);

    Team getTeamEntityById(String id);
}
