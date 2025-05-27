package com.teamconnect.service.impl;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamDeleteInput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.common.enumarator.FilePurposeType;
import com.teamconnect.common.enumarator.TeamMemberType;
import com.teamconnect.configuration.RabbitMQConfig;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.exception.TeamAlreadyExistsException;
import com.teamconnect.exception.TeamNotFoundException;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.model.nosql.File;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.postgresql.TeamRepository;
import com.teamconnect.service.FileService;
import com.teamconnect.service.TeamService;

import java.io.IOException;
import java.util.List;

import com.teamconnect.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TeamServiceImpl implements TeamService {
    private final FileService fileService;
    private final TeamRepository teamRepository;
    private final UserService userService;

    public TeamServiceImpl(
        TeamRepository teamRepository,
        RabbitMQConfig rabbitMQConfig,
        UserService userService,
        FileService fileService
    ) {
        this.teamRepository = teamRepository;
        this.userService = userService;
        this.fileService = fileService;
    }

    @Override

    public TeamDto createTeam(String userEmail, TeamCreateInput teamCreateInput, MultipartFile file) throws IOException {
        validateTeamNameIsUnique(teamCreateInput.name());
        User user = userService.getUserEntityByEmail(userEmail);
        Team team = TeamMapper.INSTANCE.teamCreateInputToTeam(teamCreateInput);
        File teamIcon = fileService.storeFile(file, FilePurposeType.AVATAR, team.getId());
        team.setIcon(teamIcon.getFileUrl());
        TeamMember teamMember = createCreatorTeamMember(user, team);
        team.getMembers().add(teamMember);
        team.setOwner(user);

        Team savedTeam = teamRepository.save(team);

        return TeamMapper.INSTANCE.teamToTeamDto(savedTeam);
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
    public List<TeamDto> getTeamsByUserId(String userId) {
        List<Team> teamList = teamRepository.findUserTeams(userId);

        return TeamMapper.INSTANCE.teamListToTeamDtoList(teamList);
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
