package com.teamconnect.dto;

import java.time.Instant;
import java.util.Set;

import com.teamconnect.api.output.teamrole.TeamRolePublicOutput;
import com.teamconnect.api.output.user.UserDetailsPublicOutput;

public record TeamMemberDto(
	String id,
	UserDetailsPublicOutput user,
	Set<TeamRolePublicOutput> roles,
	String memberType,
	Instant createdAt,
	Instant updatedAt
) {}