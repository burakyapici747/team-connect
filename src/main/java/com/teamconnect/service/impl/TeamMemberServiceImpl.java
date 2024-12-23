package com.teamconnect.service.impl;

import com.teamconnect.constant.TeamConstant;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.mapper.TeamMemberMapper;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.TeamRole;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.TeamMemberRepository;
import com.teamconnect.repository.TeamRepository;
import com.teamconnect.repository.UserRepository;
import com.teamconnect.service.TeamMemberService;
import com.teamconnect.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final TeamService teamService;
    private final UserRepository userRepository;

    @Override
    public TeamMemberDto addMember(String teamId, String userId, String role) {
        if (isMember(teamId, userId)) {
            throw new IllegalStateException(TeamConstant.MEMBER_ALREADY_EXISTS);
        }

        TeamDto teamDto = teamService.getTeamByIdOrThrow(teamId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(TeamConstant.USER_NOT_FOUND, userId)));

        Team team = TeamMapper.INSTANCE.toEntity(teamDto);
        TeamMember member = TeamMember.builder()
                .team(team)
                .user(user)
                .teamRoles(Set.of())//TODO: role eklenecek
                .build();

        TeamMember savedMember = teamMemberRepository.save(member);
        return TeamMemberMapper.INSTANCE.toDto(savedMember);
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
        return TeamMemberMapper.INSTANCE.toDto(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamMemberDto> getTeamMembers(String teamId) {
        if (!teamService.existsById(teamId)) {
            throw new EntityNotFoundException(
                    String.format(TeamConstant.TEAM_NOT_FOUND, teamId));
        }
        return teamMemberRepository.findByTeamId(teamId).stream()
                .map(TeamMemberMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamMemberDto> getUserMemberships(String userId) {
        return teamMemberRepository.findByUserId(userId).stream()
                .map(TeamMemberMapper.INSTANCE::toDto)
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

    //TODO: Bu metod'un newRole parametresi degisecek yerine teamRole id alacak diye dusunuyorum
    @Override
    public void updateMemberRole(String teamId, String userId, String newRole) {
        TeamMember member = teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
                .orElseThrow(() -> new EntityNotFoundException(TeamConstant.MEMBER_NOT_FOUND));

        member.setTeamRoles(Set.of(null));
        teamMemberRepository.save(member);
    }
}