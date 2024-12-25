package com.teamconnect.api.output.team;

import java.time.Instant;
import java.util.List;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;

public record TeamPrivateDetailsOutput(
        String id,
        String name,
        String description,
        UserDetailsPublicOutput creator,
        List<TeamMemberDetailsPublicOutput> members,
        List<TeamRoleOutput> roles,
        int totalMembers,
        Instant createdAt,
        Instant updatedAt) {
}
