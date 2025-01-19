package com.teamconnect.api.input.team;

import java.time.Instant;

public record TeamPublicOutput(
    String id,
	String name,
	String description,
    String icon,
	Instant createdAt,
	Instant updatedAt
) {}
