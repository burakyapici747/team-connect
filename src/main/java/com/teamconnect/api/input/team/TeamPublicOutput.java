package com.teamconnect.api.input.team;

import java.time.Instant;

public record TeamPublicOutput(
    String id,
	String name,
	String description,
	Instant createdAt,
	Instant updatedAt
) {}
