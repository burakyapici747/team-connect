package com.teamconnect.api.output.teammember;

import com.teamconnect.api.output.teamrole.TeamRolePublicOutput;
import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import java.time.Instant;
import java.util.Set;

public record TeamMemberPublicOutput(
	String id,
	UserDetailsPublicOutput user,
	Set<TeamRolePublicOutput> roles,
    String memberType,
	Instant joinDate
) {}
