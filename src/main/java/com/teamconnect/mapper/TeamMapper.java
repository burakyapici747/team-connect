package com.teamconnect.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamPublicOutput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.api.output.team.TeamCreateOutput;
import com.teamconnect.api.output.team.TeamMemberDetailsPublicOutput;
import com.teamconnect.api.output.team.TeamPrivateDetailsOutput;
import com.teamconnect.api.output.team.TeamPublicDetailsOutput;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.model.sql.Team;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
@Component
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDto teamToTeamDto(Team team);

    Team teamCreateInputToTeam(TeamCreateInput teamCreateInput);

    TeamCreateOutput teamDtoToTeamCreateOutput(TeamDto teamDto);

    TeamPublicDetailsOutput teamDtoToTeamPublicDetailsOutput(TeamDto teamDto);

    TeamPrivateDetailsOutput teamDtoToTeamPrivateDetailsOutput(TeamDto teamDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "role", source = "memberType")
    @Mapping(target = "joinDate", source = "createdAt")
    TeamMemberDetailsPublicOutput teamMemberDtoToTeamMemberDetailsPublicOutput(TeamMemberDto teamMemberDto);

    List<TeamPublicOutput> teamDtoListToTeamPublicOutputList(List<TeamDto> teamDtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", source = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeamFromTeamUpdateInput(TeamUpdateInput input, @MappingTarget Team team);
}
