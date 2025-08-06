package com.teamconnect.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record SecureOperationInput(
    @NotBlank(message = USER_ID + " " + REQUIRED_FIELD)
    @Pattern(regexp = UUID_PATTERN, message = USER_ID + " " + UUID_MESSAGE)
    String userId,

    @NotBlank(message = PASSWORD + " " + REQUIRED_FIELD)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_MESSAGE)
    String password
) {} 