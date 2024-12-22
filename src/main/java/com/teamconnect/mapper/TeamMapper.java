package com.teamconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.teamconnect.api.input.TeamCreateInput;
import com.teamconnect.api.input.TeamUpdateInput;
import com.teamconnect.api.output.TeamOutput;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.model.sql.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    
    TeamDto toDto(Team team);
    
    @Mapping(target = "teamMembers", ignore = true)
    @Mapping(target = "teamRoles", ignore = true)
    @Mapping(target = "meetings", ignore = true)
    Team toEntity(TeamDto dto);

    Team toEntity(TeamCreateInput input);
    TeamOutput teamDtoToTeamOutput(TeamDto teamDto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamMembers", ignore = true)
    @Mapping(target = "teamRoles", ignore = true)
    @Mapping(target = "meetings", ignore = true)
    void updateTeamFromInput(TeamUpdateInput input, @MappingTarget Team team);
}
