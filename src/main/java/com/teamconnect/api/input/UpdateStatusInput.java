package com.teamconnect.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record UpdateStatusInput(
    @NotBlank(message = USER_ID + " " + REQUIRED_FIELD)
    @Pattern(regexp = UUID_PATTERN, message = USER_ID + " " + UUID_MESSAGE)
    String userId,

    @Size(max = STATUS_MAX_LENGTH, message = STATUS_LENGTH_MESSAGE)
    String statusDescription
) {} 