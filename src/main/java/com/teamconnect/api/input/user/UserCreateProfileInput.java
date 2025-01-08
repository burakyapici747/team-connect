package com.teamconnect.api.input.user;

import com.teamconnect.common.enumarator.UserStatus;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import static com.teamconnect.common.constant.ValidationConstants.*;

public record UserCreateProfileInput(
    @Size(max = BIO_MAX_LENGTH, message = BIO_LENGTH_MESSAGE)
    String bio,

    @Size(max = LANGUAGE_MAX_LENGTH, message = LANGUAGE_LENGTH_MESSAGE)
    String language,

    UserStatus userStatus,

    @Pattern(regexp = UUID_PATTERN, message = PROFILE_IMAGE_UUID_MESSAGE)
    String profileImageFileId,

    @Size(max = STATUS_DESCRIPTION_MAX_LENGTH, message = STATUS_DESCRIPTION_LENGTH_MESSAGE)
    String statusDescription
) {}
