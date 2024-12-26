package com.teamconnect.api.input.user;

import static com.teamconnect.common.constant.ValidationConstants.EMAIL;
import static com.teamconnect.common.constant.ValidationConstants.EMAIL_LENGTH_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.EMAIL_MAX_LENGTH;
import static com.teamconnect.common.constant.ValidationConstants.FIRST_NAME;
import static com.teamconnect.common.constant.ValidationConstants.FIRST_NAME_LENGTH_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.INVALID_FORMAT;
import static com.teamconnect.common.constant.ValidationConstants.LAST_NAME;
import static com.teamconnect.common.constant.ValidationConstants.LAST_NAME_LENGTH_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.NAME_MAX_LENGTH;
import static com.teamconnect.common.constant.ValidationConstants.PASSWORD;
import static com.teamconnect.common.constant.ValidationConstants.PASSWORD_PATTERN;
import static com.teamconnect.common.constant.ValidationConstants.PASSWORD_PATTERN_MESSAGE;
import static com.teamconnect.common.constant.ValidationConstants.REQUIRED_FIELD;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterInput(
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
        String lastName) {
}
