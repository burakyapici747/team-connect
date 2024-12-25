package com.teamconnect.api.input.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record UserUpdateInput(
    @NotBlank(message = FIRST_NAME_REQUIRED)
    @Size(max = NAME_MAX_LENGTH, message = FIRST_NAME_LENGTH_MESSAGE)
    String name,

    @NotBlank(message = LAST_NAME + " " + REQUIRED_FIELD)
    @Size(max = NAME_MAX_LENGTH, message = LAST_NAME_LENGTH_MESSAGE)
    String lastName
) {}
