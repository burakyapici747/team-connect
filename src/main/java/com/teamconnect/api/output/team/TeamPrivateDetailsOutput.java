package com.teamconnect.api.output.team;

import java.time.Instant;

public record TeamPrivateDetailsOutput(
        String id,
        String name,
        String description,
        Instant createdAt,
        Instant updatedAt) {
}
