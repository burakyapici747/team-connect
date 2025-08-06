package com.teamconnect.api.input.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record UserLoginInput(
    @NotBlank(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_INVALID)
    @Size(max = EMAIL_MAX_LENGTH, message = EMAIL_LENGTH_MESSAGE)
    String email,

    @NotBlank(message = PASSWORD + " " + REQUIRED_FIELD)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_MESSAGE)
    String password
) {}
