package com.teamconnect.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record DeleteUserInput(
    @NotBlank(message = USER_ID + " " + REQUIRED_FIELD)
    @Pattern(regexp = UUID_PATTERN, message = USER_ID + " " + UUID_MESSAGE)
    String userId,

    @NotBlank(message = PASSWORD + " " + REQUIRED_FIELD)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_MESSAGE)
    String password,

    @NotBlank(message = CONFIRMATION_TEXT + " " + REQUIRED_FIELD)
    @Pattern(regexp = CONFIRMATION_TEXT_PATTERN, message = CONFIRMATION_TEXT_PATTERN_MESSAGE)
    @Size(max = CONFIRMATION_TEXT_MAX_LENGTH, message = CONFIRMATION_TEXT_LENGTH_MESSAGE)
    String confirmationText
) {} 