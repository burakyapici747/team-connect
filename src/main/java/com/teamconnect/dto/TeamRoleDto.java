package com.teamconnect.dto;

import java.time.Instant;

public record TeamRoleDto(
	String id,
	String name,
	String description,
	boolean isDefault,
	Instant createdAt,
	Instant updatedAtInstant
) {}
