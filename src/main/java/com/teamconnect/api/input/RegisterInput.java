package com.teamconnect.api.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record RegisterInput(
    @NotBlank(message = EMAIL + " " + REQUIRED_FIELD)
    @Email(message = EMAIL + " " + INVALID_FORMAT)
    @Size(max = EMAIL_MAX_LENGTH, message = EMAIL_LENGTH_MESSAGE)
    String email,

    @NotBlank(message = PASSWORD + " " + REQUIRED_FIELD)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_MESSAGE)
    String password,

    @NotBlank(message = FIRST_NAME + " " + REQUIRED_FIELD)
    @Size(max = NAME_MAX_LENGTH, message = FIRST_NAME_LENGTH_MESSAGE)
    String name,

    @NotBlank(message = LAST_NAME + " " + REQUIRED_FIELD)
    @Size(max = NAME_MAX_LENGTH, message = LAST_NAME_LENGTH_MESSAGE)
    String lastName
) {}
