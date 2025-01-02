package com.teamconnect.service.impl;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.common.enumarator.TeamMemberType;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.exception.TeamAlreadyExistsException;
import com.teamconnect.exception.TeamNotFoundException;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.TeamRepository;
import com.teamconnect.service.TeamService;
import com.teamconnect.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;



@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final UserService userService;

    public TeamServiceImpl(TeamRepository teamRepository, UserService userService) {
        this.teamRepository = teamRepository;
        this.userService = userService;
    }

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
    public TeamDto updateTeam(String id, TeamUpdateInput input) {
        Team team = findById(id);

        validateTeamNameIsUniqueForUpdate(input.name(), id);

        TeamMapper.INSTANCE.updateTeamFromTeamUpdateInput(input, team);

        return TeamMapper.INSTANCE.teamToTeamDto(teamRepository.save(team));
    }

    @Override
    public void deleteTeam(String id, TeamDeleteInput input) {
        Team team = findById(id);
        teamRepository.delete(team);
    }

    @Override
    public List<TeamDto> getUserTeams(String userId) {
        User user = userService.getUserEntityById(userId);
        return user.getTeamMembers().stream()
                .map(TeamMember::getTeam)
                .map(TeamMapper.INSTANCE::teamToTeamDto)
                .toList();
    }

    @Override
    public Team getTeamEntityById(String id) {
        return findById(id);
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

    private void validateTeamNameIsUniqueForUpdate(String teamName, String teamId) {
        if (teamRepository.existsByNameAndIdNot(teamName, teamId) ) {
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
}
