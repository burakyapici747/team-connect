package com.teamconnect.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamconnect.common.constant.TeamConstant;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.mapper.TeamMemberMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.TeamMemberRepository;
import com.teamconnect.service.TeamMemberService;
import com.teamconnect.service.TeamService;
import com.teamconnect.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {
    private final TeamMemberRepository teamMemberRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final TeamMemberMapper teamMemberMapper;

    @Override
    public TeamMemberDto addMember(String teamId, String userId, String role) {
        validateMembership(teamId, userId);

        User user = userService.getUserEntityById(userId);
        Team team = teamService.getTeamEntityById(teamId);

        TeamMember member = TeamMember.builder()
                .team(team)
                .user(user)
                .teamRoles(Set.of())
                .build();

        TeamMember savedMember = teamMemberRepository.save(member);
        return teamMemberMapper.teamMemberToTeamMemberDto(savedMember);
    }

    @Override
    public void removeMember(String teamId, String userId) {
        TeamMember member = teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
                .orElseThrow(() -> new EntityNotFoundException(TeamConstant.MEMBER_NOT_FOUND));
        teamMemberRepository.delete(member);
    }

    @Override
    @Transactional(readOnly = true)
    public TeamMemberDto getMember(String teamId, String userId) {
        TeamMember member = teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
                .orElseThrow(() -> new EntityNotFoundException(TeamConstant.MEMBER_NOT_FOUND));
        return teamMemberMapper.teamMemberToTeamMemberDto(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamMemberDto> getTeamMembers(String teamId) {
        return teamMemberRepository.findByTeamId(teamId).stream()
                .map(teamMemberMapper::teamMemberToTeamMemberDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamMemberDto> getUserMemberships(String userId) {
        return teamMemberRepository.findByUserId(userId).stream()
                .map(teamMemberMapper::teamMemberToTeamMemberDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isMember(String teamId, String userId) {
        return teamMemberRepository.existsByTeamIdAndUserId(teamId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getMemberCount(String teamId) {
        return teamMemberRepository.countByTeamId(teamId);
    }

    @Override
    public void updateMemberRole(String teamId, String userId, String newRole) {
        TeamMember member = teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
                .orElseThrow(() -> new EntityNotFoundException(TeamConstant.MEMBER_NOT_FOUND));

        member.setTeamRoles(Set.of(null));
        teamMemberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamMemberDto> getTeamMembersByTeamId(String teamId) {
        return teamMemberRepository.findByTeamId(teamId).stream()
                .map(teamMemberMapper::teamMemberToTeamMemberDto)
                .toList();
    }

    private void validateMembership(String teamId, String userId) {
        if (!isMember(teamId, userId)) {
            throw new EntityNotFoundException(TeamConstant.MEMBER_NOT_FOUND);
        }
    }
}
