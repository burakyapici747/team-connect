package com.teamconnect.api.output;

import java.util.List;

public record TeamOutput(
    String id,
    String name,
    String description,
    UserOutput owner,
    List<TeamMemberOutput> members,
    List<TeamRoleOutput> roles,
    List<MeetingOutput> meetings
) {
    public record UserOutput(
        String id,
        String name,
        String email,
        String lastName
    ) {}

    public record TeamMemberOutput(
        String id,
        UserOutput user,
        String role,
        String joinDate
    ) {}

    public record TeamRoleOutput(
        String id,
        String name,
        String description,
        List<String> permissions
    ) {}

    public record MeetingOutput(
        String id,
        String title,
        String description,
        String startTime,
        String endTime,
        String status,
        List<UserOutput> participants
    ) {}
}