package com.teamconnect.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.teamconnect.common.enumarator.Availability;
import static com.teamconnect.common.constant.ValidationConstants.*;
import jakarta.validation.constraints.Pattern;

public record UpdateAvailabilityInput(
    @NotBlank(message = USER_ID + " " + REQUIRED_FIELD)
    @Pattern(regexp = UUID_PATTERN, message = USER_ID + " " + UUID_MESSAGE)
    String userId,

    @NotNull(message = AVAILABILITY + " " + REQUIRED_FIELD)
    Availability availability
) {} 