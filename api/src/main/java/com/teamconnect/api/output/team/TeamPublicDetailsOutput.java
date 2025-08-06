package com.teamconnect.api.output.team;

import java.time.Instant;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;

public record TeamPublicDetailsOutput(
        String id,
        String name,
        String description,
        UserDetailsPublicOutput creator,
        int memberCount,
        Instant createdAt) {
}
