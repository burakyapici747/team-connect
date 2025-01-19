package com.teamconnect.service;

import java.io.IOException;
import java.util.List;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.model.sql.Team;
import org.springframework.web.multipart.MultipartFile;

public interface TeamService {
    TeamDto getTeamById(String id);

    TeamDto createTeam(String userEmail, TeamCreateInput teamCreateInput, MultipartFile file) throws IOException;

    TeamDto updateTeam(String id, TeamUpdateInput input);

    void deleteTeam(String id, TeamDeleteInput input);

    List<TeamDto> getTeamsByUserId(String userId);

    Team getTeamEntityById(String id);
}
