package com.teamconnect.api.output.team;

import java.time.Instant;
import java.util.Set;

import com.teamconnect.api.output.teamrole.TeamRolePublicOutput;
import com.teamconnect.api.output.user.UserDetailsPublicOutput;

public record TeamMemberPublicOutput(
	String id,
	UserDetailsPublicOutput user,
	Set<TeamRolePublicOutput> roles,
	Instant joinDate
) {}
