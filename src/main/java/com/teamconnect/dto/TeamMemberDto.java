package com.teamconnect.dto;

import java.time.Instant;

public record TeamMemberDto(
		String id,
		UserDto user,
		String memberType,
		Instant createdAt,
		Instant updatedAt) {
}