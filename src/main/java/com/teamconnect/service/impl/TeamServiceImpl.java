package com.teamconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.common.enumarator.TeamMemberType;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.exception.TeamAlreadyExistsException;
import com.teamconnect.exception.TeamNotFoundException;
import com.teamconnect.exception.UnauthorizedAccessException;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.TeamRepository;
import com.teamconnect.service.TeamMemberService;
import com.teamconnect.service.TeamService;
import com.teamconnect.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final UserService userService;
    private final TeamMemberService teamMemberService;

    @Override
    public TeamDto createTeam(String userEmail, TeamCreateInput teamCreateInput) {
        validateTeamNameIsUnique(teamCreateInput.name());
        User user = userService.getUserEntityByEmail(userEmail);
        Team team = TeamMapper.INSTANCE.teamCreateInputToTeam(teamCreateInput);
        TeamMember teamMember = createCreatorTeamMember(user, team);
        team.getTeamMembers().add(teamMember);
        return TeamMapper.INSTANCE.teamToTeamDto(teamRepository.save(team));
    }

    @Override
    public TeamDto getTeamById(String id) {
        return TeamMapper.INSTANCE.teamToTeamDto(findById(id));
    }

    @Override
    public TeamDto updateTeam(String id, TeamUpdateInput input, String email) {
        Team team = findById(id);
        validateCreatorAccess(team, email);

        if (input.name() != null && !input.name().equals(team.getName())) {
            validateTeamNameIsUnique(input.name());
            team.setName(input.name());
        }

        if (input.description() != null) {
            team.setDescription(input.description());
        }

        return TeamMapper.INSTANCE.teamToTeamDto(teamRepository.save(team));
    }

    @Override
    public void deleteTeam(String id, TeamDeleteInput input, String email) {
        Team team = findById(id);
        validateCreatorAccess(team, email);

        if (!"DELETE".equals(input.confirmationText())) {
            throw new IllegalArgumentException("Invalid confirmation text");
        }

        teamRepository.delete(team);
    }

    @Override
    public List<TeamDto> getUserTeams(String email) {
        User user = userService.getUserEntityByEmail(email);
        return user.getTeamMembers().stream()
                .map(TeamMember::getTeam)
                .map(TeamMapper.INSTANCE::teamToTeamDto)
                .toList();
    }

    @Override
    public List<TeamMemberDto> getTeamMembersByTeamId(String teamId) {
        validateTeamExists(teamId);
        return teamMemberService.getTeamMembersByTeamId(teamId);
    }

    @Override
    public Team getTeamEntityById(String id) {
        return findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return teamRepository.existsById(id);
    }

    @Override
    public TeamMemberDto addMember(String teamId, String userId, String role) {
        return null;
    }

    private void validateTeamExists(String teamId) {
        if (!existsById(teamId)) {
            throw new TeamNotFoundException("Team not found with id: " + teamId);
        }
    }

    private Team findById(String id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found"));
    }

    private void validateTeamNameIsUnique(String teamName) {
        if (teamRepository.existsByName(teamName)) {
            throw new TeamAlreadyExistsException("A team with this name already exists");
        }
    }

    private TeamMember createCreatorTeamMember(User user, Team team) {
        TeamMember teamMember = new TeamMember();
        teamMember.setTeam(team);
        teamMember.setUser(user);
        teamMember.setMemberType(TeamMemberType.CREATOR);
        return teamMember;
    }

    private void validateCreatorAccess(Team team, String email) {
        User currentUser = userService.getUserEntityByEmail(email);
        boolean isCreator = team.getTeamMembers().stream()
                .anyMatch(member -> member.getUser().getId().equals(currentUser.getId())
                        && member.getMemberType() == TeamMemberType.CREATOR);

        if (!isCreator) {
            throw new UnauthorizedAccessException("Only team creator can perform this operation");
        }
    }
}
