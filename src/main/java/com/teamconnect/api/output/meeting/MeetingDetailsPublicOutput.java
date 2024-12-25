package com.teamconnect.api.output.meeting;

import java.time.Instant;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.common.enumarator.MeetingStatus;

public record MeetingDetailsPublicOutput(
        String id,
        String title,
        String description,
        String teamId,
        MeetingStatus status,
        Instant startTime,
        Instant endTime,
        UserDetailsPublicOutput creator,
        int participantCount,
        Instant createdAt) {
}