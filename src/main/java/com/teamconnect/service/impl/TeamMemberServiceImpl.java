package com.teamconnect.service.impl;

import com.teamconnect.api.input.TeamMemberCreateInput;
import com.teamconnect.api.input.TeamMemberRoleAssignInput;
import com.teamconnect.common.enumarator.TeamMemberType;
import com.teamconnect.dto.TeamMemberDto;
import com.teamconnect.mapper.TeamMemberMapper;
import com.teamconnect.model.sql.Team;
import com.teamconnect.model.sql.TeamMember;
import com.teamconnect.model.sql.TeamRole;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.TeamMemberRepository;
import com.teamconnect.service.TeamMemberService;
import com.teamconnect.service.TeamRoleService;
import com.teamconnect.service.TeamService;
import com.teamconnect.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    private final TeamMemberRepository teamMemberRepository;
    private final TeamService teamService;
    private final TeamRoleService teamRoleService;
    private final UserService userService;
    private final TeamMemberMapper teamMemberMapper;

    public TeamMemberServiceImpl(
        TeamMemberRepository teamMemberRepository,
        TeamService teamService,
        TeamRoleService teamRoleService,
        UserService userService,
        TeamMemberMapper teamMemberMapper
    ) {
        this.teamMemberRepository = teamMemberRepository;
        this.teamService = teamService;
        this.teamRoleService = teamRoleService;
        this.userService = userService;
        this.teamMemberMapper = teamMemberMapper;
    }

    @Override
    public List<TeamMemberDto> getTeamMembersByTeamId(String teamId) {
        return teamMemberRepository.findByTeamIdWithUserAndRoles(teamId).stream()
            .map(teamMemberMapper::teamMemberToTeamMemberDto)
            .toList();
    }

    @Override
    public TeamMemberDto addMember(String teamId, TeamMemberCreateInput input) {
        Team team = teamService.getTeamEntityById(teamId);
        User user = userService.getUserEntityById(input.userId());

        validateGivenUserAlreadyInTeam(teamId, input.userId());

        TeamMember teamMember = new TeamMember();
        teamMember.setUser(user);
        teamMember.setTeam(team);
        teamMember.setMemberType(TeamMemberType.MEMBER);
        team.getTeamMembers().add(teamMember);

        return teamMemberMapper.teamMemberToTeamMemberDto(teamMemberRepository.save(teamMember));
    }

    @Override
    public void removeMember(String teamId, String memberId) {
        Team team = teamService.getTeamEntityById(teamId);
        TeamMember teamMember = findTeamMemberById(memberId);
        User user = userService.getUserEntityById(teamMember.getUser().getId());

        validateMemberBelongsToTeam(team, teamMember);

        team.getTeamMembers().remove(teamMember);
        user.getTeamMembers().remove(teamMember);
    }

    @Override
    public void removeRoleFromMember(String teamId, String teamMemberId, String roleId) {
        Team team = teamService.getTeamEntityById(teamId);
        TeamMember teamMember = findTeamMemberById(teamMemberId);
        TeamRole teamRole = teamRoleService.findTeamRoleById(roleId);

        validateRoleBelongsToTeam(team, teamRole);
        validateMemberBelongsToTeam(team, teamMember);

        teamMember.getTeamRoles().remove(teamRole);
    }

    @Override
    public void assignRoleToMember(
        String memberId,
        TeamMemberRoleAssignInput input
    ) {
        Team team = teamService.getTeamEntityById(input.teamId());
        TeamRole teamRole = teamRoleService.findTeamRoleById(input.roleId());
        TeamMember teamMember = findTeamMemberById(memberId);

        validateRoleBelongsToTeam(team, teamRole);
        validateMemberBelongsToTeam(team, teamMember);

        teamMember.getTeamRoles().add(teamRole);

        teamMemberRepository.save(teamMember);
    }

    private TeamMember findTeamMemberById(String memberId) {
        return teamMemberRepository.findById(memberId)
            .orElseThrow(() -> new EntityNotFoundException("Team member not found"));
    }

    private void validateMemberBelongsToTeam(Team team, TeamMember member) {
        if (!isMemberInTeam(team, member)) {
            throw new EntityNotFoundException("Member does not belong to this team");
        }
    }

    private void validateRoleBelongsToTeam(Team team, TeamRole role) {
        if (team.getTeamRoles().stream()
                .noneMatch(r -> r.getId().equals(role.getId()))) {
            throw new EntityNotFoundException("Role does not belong to this team");
        }
    }

    private boolean isMemberInTeam(Team team, TeamMember member) {
        return team.getTeamMembers().stream()
            .anyMatch(m -> m.getId().equals(member.getId()));
    }

    private void validateGivenUserAlreadyInTeam(String teamId, String userId) {
        if (teamMemberRepository.findByTeamIdAndUserId(teamId, userId).isPresent()) {
            throw new EntityExistsException("User is already in the team");
        }
    }
}
