package com.teamconnect.mapper;

import com.teamconnect.api.input.team.TeamCreateInput;
import com.teamconnect.api.input.team.TeamPublicOutput;
import com.teamconnect.api.input.team.TeamUpdateInput;
import com.teamconnect.api.output.team.TeamCreateOutput;
import com.teamconnect.api.output.team.TeamPublicDetailsOutput;
import com.teamconnect.api.output.teammember.TeamMemberPublicOutput;
import com.teamconnect.api.output.teammember.TeamPrivateOutput;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.model.sql.Team;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDto teamToTeamDto(Team team);

    Team teamCreateInputToTeam(TeamCreateInput teamCreateInput);

    TeamCreateOutput teamDtoToTeamCreateOutput(TeamDto teamDto);

    TeamPublicDetailsOutput teamDtoToTeamPublicDetailsOutput(TeamDto teamDto);

    TeamPrivateOutput teamDtoToTeamPrivateDetailsOutput(TeamDto teamDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "memberType", source = "memberType")
    @Mapping(target = "joinDate", source = "createdAt")
    TeamMemberPublicOutput teamMemberDtoToTeamMemberDetailsPublicOutput(TeamMemberDto teamMemberDto);

    List<TeamDto> teamListToTeamDtoList(List<Team> teamList);

    List<TeamPublicOutput> teamDtoListToTeamPublicOutputList(List<TeamDto> teamDtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", source = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeamFromTeamUpdateInput(TeamUpdateInput input, @MappingTarget Team team);
}
