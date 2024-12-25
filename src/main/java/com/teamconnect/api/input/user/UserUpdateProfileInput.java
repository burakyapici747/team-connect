package com.teamconnect.api.input.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;
import com.teamconnect.common.enumarator.Availability;

public record UserUpdateProfileInput(
    @NotBlank(message = USER_ID + " " + REQUIRED_FIELD)
    @Pattern(regexp = UUID_PATTERN, message = USER_ID + " " + UUID_MESSAGE)
    String userId,

    @Size(max = BIO_MAX_LENGTH, message = BIO_LENGTH_MESSAGE)
    String bio,

    @Size(max = LANGUAGE_MAX_LENGTH, message = LANGUAGE_LENGTH_MESSAGE)
    String language,

    Availability availability,

    @Pattern(regexp = UUID_PATTERN, message = PROFILE_IMAGE_UUID_MESSAGE)
    String profileImageFileId,

    @Size(max = STATUS_DESCRIPTION_MAX_LENGTH, message = STATUS_DESCRIPTION_LENGTH_MESSAGE)
    String statusDescription
) {}
