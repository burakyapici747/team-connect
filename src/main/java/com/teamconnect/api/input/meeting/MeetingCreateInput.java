package com.teamconnect.api.input.meeting;

import java.time.Instant;
import java.util.Set;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MeetingCreateInput(
        @NotBlank(message = "Meeting title is required") @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters") String title,

        @Size(max = 500, message = "Description cannot exceed 500 characters") String description,

        @NotNull(message = "Team ID is required") String teamId,

        @NotNull(message = "Start time is required") @Future(message = "Start time must be in the future") Instant startTime,

        @NotNull(message = "End time is required") @Future(message = "End time must be in the future") Instant endTime,

        Set<String> participantIds) {
}