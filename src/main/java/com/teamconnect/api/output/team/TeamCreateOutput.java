package com.teamconnect.api.output.team;

import java.time.Instant;

public record TeamCreateOutput(
    String id,
    String name,
    String description,
    Instant createdAt,
    String iconUrl
) {}
