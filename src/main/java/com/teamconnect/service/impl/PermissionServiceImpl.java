package com.teamconnect.service.impl;

import org.springframework.stereotype.Service;

import com.teamconnect.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
//
//    private final TeamMemberRepository teamMemberRepository;
//
//    @Override
//    public boolean hasTeamPermission(String teamId, String userId, TeamPermission permission) {
//        return teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
//                .map(member -> {
//                    if (member.getMemberType() == TeamMemberType.CREATOR) {
//                        return true;
//                    }
//                    return member.getTeamRoles().stream()
//                            .anyMatch(role -> role.getPermissions().contains(permission));
//                })
//                .orElse(false);
//    }
//
//    @Override
//    public boolean isTeamCreator(String teamId, String userId) {
//        return teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
//                .map(member -> member.getMemberType() == TeamMemberType.CREATOR)
//                .orElse(false);
//    }
//
//    @Override
//    public void validateTeamPermission(
//        String teamId,
//        String userId,
//        List<TeamPermission> teamPermissions,
//        boolean allTeamPermissionRequired,
//        boolean isSelfUserPermission,
//        boolean justTeamMember
//    ) {
//        TeamMember member = teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
//                .orElseThrow(() -> new UnauthorizedAccessException("User is not a member of this team"));
//
//        if (member.getMemberType() == TeamMemberType.CREATOR || isSelfUserPermission || justTeamMember) {
//            return;
//        }
//
//        Set<TeamPermission> userPermissions = member.getTeamRoles().stream()
//                .flatMap(role -> role.getPermissions().stream())
//                .collect(Collectors.toSet());
//
//        boolean hasRequiredPermissions = allTeamPermissionRequired
//                ? userPermissions.containsAll(teamPermissions)
//                : teamPermissions.stream().anyMatch(userPermissions::contains);
//
//        if (!hasRequiredPermissions) {
//            throw new UnauthorizedAccessException("User does not have required permissions");
//        }
//    }
}
