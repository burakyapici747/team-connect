package com.teamconnect.api.input.message;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;

public record GetMessageQueryParams(
    @Max(100) int limit,
    String before,
    String after
) {
    public GetMessageQueryParams {
        if (limit <= 0) {
            limit = 50;
        }
    }

    @AssertTrue(message = "The 'before' and 'after' parameters cannot be used at the same time.")
    public boolean isQueryValid() {
        return !(before != null && after != null);
    }
}
