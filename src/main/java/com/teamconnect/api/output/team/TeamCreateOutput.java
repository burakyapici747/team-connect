package com.teamconnect.api.output.team;

import java.time.Instant;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;

public record TeamCreateOutput(
        String id,
        String name,
        String description,
        UserDetailsPublicOutput creator,
        Instant createdAt) {
}
