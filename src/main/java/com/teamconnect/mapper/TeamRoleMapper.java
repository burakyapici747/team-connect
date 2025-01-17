package com.teamconnect.mapper;

import com.teamconnect.api.input.team.TeamRoleCreateInput;
import com.teamconnect.api.input.team.TeamRoleUpdateInput;
import com.teamconnect.api.output.teamrole.TeamRoleOutput;
import com.teamconnect.api.output.teamrole.TeamRolePrivateOutput;
import com.teamconnect.api.output.teamrole.TeamRolePublicOutput;
import com.teamconnect.dto.TeamRoleDto;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TeamRoleMapper {
//    TeamRoleMapper INSTANCE = Mappers.getMapper(TeamRoleMapper.class);
//
//    TeamRoleDto teamRoleToTeamRoleDto(TeamRole teamRole);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "permissions", expression = "java(input.permissions().stream().map(permission -> TeamPermission.valueOf(permission.getValue())).collect(java.util.stream.Collectors.toSet()))")
//    TeamRole teamRoleCreateInputToTeamRole(TeamRoleCreateInput input);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "description", source = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "permissions", source = "permissions", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateTeamRoleFromUpdateInput(TeamRoleUpdateInput input, @MappingTarget TeamRole teamRole);
//
//    TeamRoleOutput teamRoleDtoToTeamRoleOutput(TeamRoleDto teamRoleDto);
//
//    List<TeamRolePublicOutput> teamRoleDtoListToTeamRolePublicOutputList(List<TeamRoleDto> teamRoleDtoList);
//
//    List<TeamRolePrivateOutput> teamRoleDtoListToTeamRolePrivateOutputList(List<TeamRoleDto> teamRoleDtoList);
}
