package com.teamconnect.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.teamconnect.api.input.TeamMemberCreateInput;
import com.teamconnect.api.output.team.TeamMemberPublicOutput;
import com.teamconnect.api.output.teammember.TeamMemberOutput;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.model.sql.TeamMember;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TeamRoleMapper.class})
@Component
public interface TeamMemberMapper {
    TeamMemberMapper INSTANCE = Mappers.getMapper(TeamMemberMapper.class);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "memberType", source = "memberType")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "roles", source = "teamRoles")
    TeamMemberDto teamMemberToTeamMemberDto(TeamMember teamMember);

    @Mapping(target = "teamRoles", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "user", ignore = true)
    TeamMember createInputToEntity(TeamMemberCreateInput input);

    TeamMemberOutput teamMemberDtoToTeamMemberOutput(TeamMemberDto dto);


    @Mapping(target = "user", source = "user")
    @Mapping(target = "memberType", source = "memberType")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "roles", source = "teamRoles")
    List<TeamMemberDto> teamMembersToTeamMemberDtos(List<TeamMember> teamMembers);

    List<TeamMemberPublicOutput> teamMemberDtoListToTeamMemberPublicOutputList(List<TeamMemberDto> teamMemberDtoList);

    @Mapping(target = "joinDate", source = "createdAt")
    TeamMemberPublicOutput teamMemberDtoToTeamMemberPublicOutput(TeamMemberDto teamMemberDto);
}
