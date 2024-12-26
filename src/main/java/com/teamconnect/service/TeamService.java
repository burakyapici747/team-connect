package com.teamconnect.service;

import java.util.List;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.model.sql.Team;

public interface TeamService {
    // Public API methods (DTO based)
    TeamDto createTeam(String userEmail, TeamCreateInput teamCreateInput);

    TeamDto getTeamById(String id);

    TeamDto updateTeam(String id, TeamUpdateInput input, String email);

    void deleteTeam(String id, TeamDeleteInput input, String email);

    List<TeamDto> getUserTeams(String email);

    List<TeamMemberDto> getTeamMembersByTeamId(String teamId);

    // Internal methods for service-to-service communication (Entity based)
    Team getTeamEntityById(String id);

    boolean existsById(String id);

    TeamMemberDto addMember(String teamId, String userId, String role);
}
