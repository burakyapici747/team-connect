package com.teamconnect.api.input.user;

import jakarta.validation.constraints.NotNull;
import com.teamconnect.common.enumarator.UserStatus;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record UserUpdateAvailabilityInput(
    @NotNull(message = AVAILABILITY + " " + REQUIRED_FIELD)
    UserStatus userStatus
) {}
