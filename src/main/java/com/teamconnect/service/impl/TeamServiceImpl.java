package com.teamconnect.service.impl;

import com.teamconnect.constant.TeamConstant;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.repository.TeamRepository;
import com.teamconnect.repository.TeamMemberRepository;
import com.teamconnect.service.TeamService;
import com.teamconnect.api.input.TeamCreateInput;
import com.teamconnect.api.input.TeamUpdateInput;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    public TeamDto createTeam(TeamCreateInput input) {
        Team team = TeamMapper.INSTANCE.toEntity(input);
        Team savedTeam = teamRepository.save(team);
        return TeamMapper.INSTANCE.toDto(savedTeam);
    }

    @Override
    public TeamDto updateTeam(String id, TeamUpdateInput input) {
        Team team = getTeamEntity(id);
        TeamMapper.INSTANCE.updateTeamFromInput(input, team);
        Team updatedTeam = teamRepository.save(team);
        return TeamMapper.INSTANCE.toDto(updatedTeam);
    }

    @Override
    public void deleteTeam(String id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(TeamConstant.TEAM_NOT_FOUND, id));
        }
        teamRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDto getTeamById(String id) {
        Team team = getTeamEntity(id);
        return TeamMapper.INSTANCE.toDto(team);
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDto getTeamByName(String name) {
        Team team = teamRepository.findByNameWithDetails(name)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(TeamConstant.TEAM_NOT_FOUND, name)));
        return TeamMapper.INSTANCE.toDto(team);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> getAllTeams() {
        return teamRepository.findAllWithDetails().stream()
                .map(TeamMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> getTeamsByMemberId(String userId) {
        return teamMemberRepository.findByUserId(userId).stream()
                .map(TeamMember::getTeam)
                .map(TeamMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserTeamMember(String teamId, String userId) {
        return teamMemberRepository.existsByTeamIdAndUserId(teamId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return teamRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDto getTeamByIdOrThrow(String id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(TeamConstant.TEAM_NOT_FOUND, id)));
        return TeamMapper.INSTANCE.toDto(team);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(String id) {
        return teamRepository.existsById(id);
    }

    // Yardımcı metod - Entity getirme
    private Team getTeamEntity(String id) {
        return teamRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(TeamConstant.TEAM_NOT_FOUND, id)));
    }
}
