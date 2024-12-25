package com.teamconnect.api.output.meeting;

import java.time.Instant;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;

public record MeetingParticipantDetailsOutput(
        String id,
        UserDetailsPublicOutput user,
        boolean isAttending,
        String responseNote,
        Instant joinedAt) {
}