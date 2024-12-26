package com.teamconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.mapper.TeamMemberMapper;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.TeamRole;
import com.teamconnect.repository.TeamMemberRepository;
import com.teamconnect.repository.TeamRoleRepository;
import com.teamconnect.service.TeamMemberService;
import com.teamconnect.service.TeamService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {
    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public List<TeamMemberDto> getTeamMembersByTeamId(String teamId) {
        return teamMemberRepository.findByTeamId(teamId).stream()
                .map(TeamMemberMapper.INSTANCE::teamMemberToTeamMemberDto)
                .toList();
    }

    @Override
    public void removeMember(String teamId, String userId) {
        TeamMember member = teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Team member not found"));
        teamMemberRepository.delete(member);
    }
}
