package com.teamconnect.api.output.teammember;

import java.time.Instant;

public record TeamPrivateOutput(
	String id,
	String name,
	String description,
	Instant createdAt,
	Instant updatedAt
) {}
