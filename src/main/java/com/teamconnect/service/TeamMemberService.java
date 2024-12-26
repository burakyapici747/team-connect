package com.teamconnect.service;

import java.util.List;

import com.teamconnect.dto.TeamMemberDto;

public interface TeamMemberService {
    List<TeamMemberDto> getTeamMembersByTeamId(String teamId);

    void removeMember(String teamId, String userId);
}
