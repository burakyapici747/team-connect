package com.teamconnect.api.input.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record UserUpdateInput(
    @NotBlank(message = FIRST_NAME_REQUIRED)
    @Size(max = NAME_MAX_LENGTH, message = FIRST_NAME_LENGTH_MESSAGE)
    String username
) {}
