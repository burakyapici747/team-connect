package com.teamconnect.api.input.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record UserUpdatePasswordInput(
    @NotBlank(message = PASSWORD + " " + REQUIRED_FIELD)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_MESSAGE)
    String password
) {}
