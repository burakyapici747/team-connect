package com.teamconnect.api.input.user;

import static com.teamconnect.common.constant.ValidationConstants.BIO_LENGTH_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.BIO_MAX_LENGTH;
import static com.teamconnect.common.constant.ValidationConstants.LANGUAGE_LENGTH_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.LANGUAGE_MAX_LENGTH;
import static com.teamconnect.common.constant.ValidationConstants.PROFILE_IMAGE_UUID_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.STATUS_DESCRIPTION_LENGTH_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.STATUS_DESCRIPTION_MAX_LENGTH;
import static com.teamconnect.common.constant.ValidationConstants.UUID_PATTERN;

import com.teamconnect.common.enumarator.Availability;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateProfileInput(
        @Size(max = BIO_MAX_LENGTH, message = BIO_LENGTH_MESSAGE) String bio,

        @Size(max = LANGUAGE_MAX_LENGTH, message = LANGUAGE_LENGTH_MESSAGE) String language,

        Availability availability,

        @Pattern(regexp = UUID_PATTERN, message = PROFILE_IMAGE_UUID_MESSAGE) String profileImageFileId,

        @Size(max = STATUS_DESCRIPTION_MAX_LENGTH, message = STATUS_DESCRIPTION_LENGTH_MESSAGE) String statusDescription) {
}
