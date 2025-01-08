package com.teamconnect.dto;

import java.time.Instant;
import java.util.Set;

public record TeamRoleDto(
	String id,
	String name,
	String description,
	Set<TeamPermission> permissions,
	boolean isDefault,
	Instant createdAt,
	Instant updatedAtInstant
) {}
