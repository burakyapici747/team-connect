package com.teamconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.teamconnect.api.input.team.TeamRoleCreateInput;
import com.teamconnect.api.input.team.TeamRoleUpdateInput;
import com.teamconnect.api.output.team.TeamRoleOutput;
import com.teamconnect.dto.TeamRoleDto;
import com.teamconnect.model.sql.TeamRole;

@Mapper(componentModel = "spring")
public interface TeamRoleMapper {
    TeamRoleMapper INSTANCE = Mappers.getMapper(TeamRoleMapper.class);

    TeamRoleDto teamRoleToTeamRoleDto(TeamRole teamRole);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TeamRole teamRoleCreateInputToTeamRole(TeamRoleCreateInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", source = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "permissions", source = "permissions", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeamRoleFromUpdateInput(TeamRoleUpdateInput input, @MappingTarget TeamRole teamRole);

    TeamRoleOutput teamRoleDtoToTeamRoleOutput(TeamRoleDto teamRoleDto);
}
