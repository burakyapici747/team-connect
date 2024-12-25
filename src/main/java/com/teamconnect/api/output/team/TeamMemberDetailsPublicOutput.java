package com.teamconnect.api.output.team;

import java.time.Instant;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;

public record TeamMemberDetailsPublicOutput(
        String id,
        UserDetailsPublicOutput user,
        String role,
        Instant joinDate) {
}