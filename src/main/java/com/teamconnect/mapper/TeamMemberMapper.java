package com.teamconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.teamconnect.api.input.TeamMemberCreateInput;
import com.teamconnect.api.output.TeamMemberOutput;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.model.sql.TeamMember;

@Mapper(componentModel = "spring")
public interface TeamMemberMapper {
    TeamMemberMapper INSTANCE = Mappers.getMapper(TeamMemberMapper.class);

    @Mapping(target = "teamId", source = "team.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "teamName", source = "team.name")
    @Mapping(target = "joinDate", source = "joinedAt")
    TeamMemberDto toDto(TeamMember teamMember);

    @Mapping(target = "team.id", source = "teamId")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "joinedAt", source = "joinDate")
    @Mapping(target = "teamRoles", ignore = true)
    TeamMember toEntity(TeamMemberDto dto);

    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "team.name", source = "teamName")
    @Mapping(target = "team.id", source = "teamId")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "joinDate", source = "joinDate")
    TeamMemberOutput toOutput(TeamMemberDto dto);
    
    @Mapping(target = "joinedAt", ignore = true)
    @Mapping(target = "teamRoles", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "user", ignore = true)
    TeamMember createInputToEntity(TeamMemberCreateInput input);
} 