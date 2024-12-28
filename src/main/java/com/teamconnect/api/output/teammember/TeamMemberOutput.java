package com.teamconnect.api.output;

import java.time.Instant;

public record TeamMemberOutput(
    Long id,
    UserOutput user,
    TeamOutput team,
    Instant joinDate
) {
    public record UserOutput(
        Long id,
        String email
    ) {}

    public record TeamOutput(
        Long id,
        String name,
        String description
    ) {}
} 