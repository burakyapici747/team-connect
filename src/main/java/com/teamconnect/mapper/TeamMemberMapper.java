package com.teamconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.teamconnect.api.input.TeamMemberCreateInput;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.model.sql.TeamMember;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TeamMemberMapper {
    TeamMemberMapper INSTANCE = Mappers.getMapper(TeamMemberMapper.class);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "memberType", source = "memberType")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    TeamMemberDto teamMemberToTeamMemberDto(TeamMember teamMember);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "memberType", source = "memberType")
    TeamMember teamMemberDtoToTeamMember(TeamMemberDto dto);

    @Mapping(target = "teamRoles", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "user", ignore = true)
    TeamMember createInputToEntity(TeamMemberCreateInput input);
}
