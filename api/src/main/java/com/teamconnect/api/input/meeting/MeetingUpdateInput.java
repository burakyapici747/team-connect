package com.teamconnect.api.input.meeting;

import java.time.Instant;
import java.util.Set;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

public record MeetingUpdateInput(
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters") String title,

        @Size(max = 500, message = "Description cannot exceed 500 characters") String description,

        @Future(message = "Start time must be in the future") Instant startTime,

        @Future(message = "End time must be in the future") Instant endTime,

        Set<String> participantIds) {
}