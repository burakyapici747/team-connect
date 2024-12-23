package com.teamconnect.service;

import com.teamconnect.dto.TeamDto;
import com.teamconnect.api.input.TeamCreateInput;
import com.teamconnect.api.input.TeamUpdateInput;
import java.util.List;

public interface TeamService {
    // Temel CRUD operasyonları
    TeamDto createTeam(TeamCreateInput input);
    TeamDto updateTeam(String id, TeamUpdateInput input);
    void deleteTeam(String id);
    TeamDto getTeamById(String id);
    TeamDto getTeamByName(String name);
    List<TeamDto> getAllTeams();
    
    // Takım üyeliği operasyonları
    List<TeamDto> getTeamsByMemberId(String userId);
    
    // Kontrol metodları
    boolean isUserTeamMember(String teamId, String userId);
    boolean existsByName(String name);

    // TeamMemberService için yardımcı metodlar
    TeamDto getTeamByIdOrThrow(String id);
    boolean existsById(String id);
}
