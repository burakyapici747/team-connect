package com.teamconnect.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.output.team.TeamCreateOutput;
import com.teamconnect.api.output.team.TeamMemberDetailsPublicOutput;
import com.teamconnect.api.output.team.TeamPrivateDetailsOutput;
import com.teamconnect.api.output.team.TeamPublicDetailsOutput;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.model.sql.Team;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDto teamToTeamDto(Team team);

    Team teamCreateInputToTeam(TeamCreateInput teamCreateInput);

    @Mapping(target = "creator", source = "teamMembers", qualifiedByName = "findCreator")
    TeamCreateOutput teamDtoToTeamCreateOutput(TeamDto teamDto);

    @Mapping(target = "creator", source = "teamMembers", qualifiedByName = "findCreator")
    @Mapping(target = "memberCount", expression = "java(teamDto.getTeamMembers().size())")
    TeamPublicDetailsOutput teamDtoToTeamPublicDetailsOutput(TeamDto teamDto);

    @Mapping(target = "creator", source = "teamMembers", qualifiedByName = "findCreator")
    @Mapping(target = "totalMembers", expression = "java(teamDto.getTeamMembers().size())")
    TeamPrivateDetailsOutput teamDtoToTeamPrivateDetailsOutput(TeamDto teamDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "role", source = "memberType")
    @Mapping(target = "joinDate", source = "createdAt")
    TeamMemberDetailsPublicOutput teamMemberDtoToTeamMemberDetailsPublicOutput(TeamMemberDto teamMemberDto);

    List<TeamMemberDetailsPublicOutput> teamMemberDtosToTeamMemberDetailsPublicOutputs(
            List<TeamMemberDto> teamMemberDtos);
}
