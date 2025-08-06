package com.teamconnect.api.input.message;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.UUID;

public record GetMessageQueryParams(
    UUID around,
    UUID before,
    UUID after,

    @Min(value = 1, message = "Limit must be at least 1")
    @Max(value = 100, message = "Limit cannot exceed 100")
    Integer limit
) {
    public GetMessageQueryParams {
        if (limit == null || limit <= 0) {
            limit = 50;
        }
    }

    @AssertTrue(message = "Cannot use both 'before' and 'after' parameters simultaneously")
    public boolean isQueryValid() {
        return before == null || after == null;
    }

    public int getEffectiveLimit() {
        return limit != null ? limit : 50;
    }

    public boolean hasCursor() {
        return before != null || after != null;
    }
}
