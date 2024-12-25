package com.teamconnect.api.output.team;

import java.time.Instant;

public record TeamMemberPublicOutput(
        String id,
        UserPublicOutput user,
        String role,
        Instant joinDate) {
    public record UserPublicOutput(
            String id,
            String name,
            String lastName,
            String email) {
    }
}
