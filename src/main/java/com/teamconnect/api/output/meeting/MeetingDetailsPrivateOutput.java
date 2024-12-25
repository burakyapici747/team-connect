package com.teamconnect.api.output.meeting;

import java.time.Instant;
import java.util.List;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.common.enumarator.MeetingStatus;

public record MeetingDetailsPrivateOutput(
        String id,
        String title,
        String description,
        String teamId,
        MeetingStatus status,
        Instant startTime,
        Instant endTime,
        UserDetailsPublicOutput creator,
        List<MeetingParticipantDetailsOutput> participants,
        Instant createdAt,
        Instant updatedAt) {
}