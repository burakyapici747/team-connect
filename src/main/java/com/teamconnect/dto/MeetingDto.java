package com.teamconnect.dto;

import java.time.Instant;
import java.util.List;

import com.teamconnect.common.enumarator.MeetingStatus;

public record MeetingDto(
        String id,
        String title,
        String description,
        String teamId,
        MeetingStatus status,
        Instant startTime,
        Instant endTime,
        UserDto creator,
        List<MeetingParticipantDto> participants,
        Instant createdAt,
        Instant updatedAt) {
    public record MeetingParticipantDto(
            String id,
            UserDto user,
            boolean isAttending,
            String responseNote,
            Instant joinedAt) {
    }
}