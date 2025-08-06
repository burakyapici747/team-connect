package com.teamconnect.api.output.meeting;

import java.time.Instant;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.common.enumarator.MeetingStatus;

public record MeetingCreateOutput(
        String id,
        String title,
        String description,
        String teamId,
        MeetingStatus status,
        Instant startTime,
        Instant endTime,
        UserDetailsPublicOutput creator,
        Instant createdAt) {
}