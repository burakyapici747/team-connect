package com.teamconnect.service;

import java.util.List;
import com.teamconnect.dto.TeamMemberDto;

public interface TeamMemberService {
    TeamMemberDto addMember(String teamId, String userId, String role);
    void removeMember(String teamId, String userId);
    TeamMemberDto getMember(String teamId, String userId);
    List<TeamMemberDto> getTeamMembers(String teamId);
    List<TeamMemberDto> getUserMemberships(String userId);
    boolean isMember(String teamId, String userId);
    long getMemberCount(String teamId);
    void updateMemberRole(String teamId, String userId, String newRole);
} 