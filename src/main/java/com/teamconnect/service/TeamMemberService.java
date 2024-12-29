package com.teamconnect.service;

import java.util.List;

import com.teamconnect.api.input.TeamMemberCreateInput;
import com.teamconnect.api.input.TeamMemberRoleAssignInput;
import com.teamconnect.dto.TeamMemberDto;

public interface TeamMemberService {
    List<TeamMemberDto> getTeamMembersByTeamId(String teamId);

    TeamMemberDto addMember(String teamId, TeamMemberCreateInput input);

    void removeMember(String teamId, String userId);

    void removeRoleFromMember(String teamId, String teamMemberId, String roleId);

    void assignRoleToMember(String memberId, TeamMemberRoleAssignInput input);
}
