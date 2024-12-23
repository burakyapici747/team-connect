package com.teamconnect.common.constant;

public final class ValidationConstants {
    private ValidationConstants() {}

    // Common Messages
    public static final String REQUIRED_FIELD = "is required";
    public static final String INVALID_FORMAT = "Invalid format";
    public static final String EXCEED_MAX_LENGTH = "cannot exceed %d characters";
    public static final String MIN_LENGTH_MESSAGE = "must be at least %d characters";
    public static final String PATTERN_MISMATCH = "does not match required pattern";
    public static final String INVALID_ENUM = "is not a valid value";
    public static final String CHARACTERS_SUFFIX = " characters";

    // Field Names
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String FIRST_NAME = "First name";
    public static final String LAST_NAME = "Last name";
    public static final String USER_ID = "User ID";
    public static final String BIO = "Bio";
    public static final String LANGUAGE = "Language";
    public static final String STATUS = "Status";
    public static final String AVAILABILITY = "Availability status";
    public static final String CONFIRMATION_TEXT = "Confirmation text";
    public static final String CURRENT_PASSWORD = "Current password";
    public static final String NEW_PASSWORD = "New password";
    public static final String PROFILE_IMAGE = "Profile image";
    
    // Size Constraints
    public static final int EMAIL_MAX_LENGTH = 100;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int BIO_MAX_LENGTH = 500;
    public static final int LANGUAGE_MAX_LENGTH = 100;
    public static final int STATUS_MAX_LENGTH = 100;
    public static final int CONFIRMATION_TEXT_MAX_LENGTH = 100;
    public static final int UUID_LENGTH = 36;
    public static final int STATUS_DESCRIPTION_MAX_LENGTH = 100;

    // Regex Patterns
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public static final String PASSWORD_PATTERN_MESSAGE = 
        "Password must be at least 8 characters and contain at least one letter and one number";
    public static final String UUID_PATTERN = 
        "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    public static final String UUID_MESSAGE = "Must be a valid UUID format";
    public static final String CONFIRMATION_TEXT_PATTERN = "^DELETE$";
    public static final String CONFIRMATION_TEXT_PATTERN_MESSAGE = "Confirmation text must be exactly 'DELETE'";

    // Validation Messages
    public static final String EMAIL_PATTERN_MESSAGE = "Must be a valid email address";
    public static final String CURRENT_PASSWORD_MESSAGE = CURRENT_PASSWORD + " " + REQUIRED_FIELD;
    public static final String NEW_PASSWORD_MESSAGE = NEW_PASSWORD + " " + REQUIRED_FIELD;
    public static final String PROFILE_IMAGE_UUID_MESSAGE = PROFILE_IMAGE + " ID " + UUID_MESSAGE;

    // Size Messages
    public static final String PASSWORD_MIN_LENGTH_MESSAGE = PASSWORD + " " + String.format(MIN_LENGTH_MESSAGE, PASSWORD_MIN_LENGTH);
    public static final String CURRENT_PASSWORD_MIN_LENGTH_MESSAGE = CURRENT_PASSWORD + " " + String.format(MIN_LENGTH_MESSAGE, PASSWORD_MIN_LENGTH);

    // Message templates
    public static final String EMAIL_LENGTH_MESSAGE = "Email must not exceed " + EMAIL_MAX_LENGTH + CHARACTERS_SUFFIX;
    public static final String FIRST_NAME_LENGTH_MESSAGE = "First name cannot exceed 50 characters";
    public static final String LAST_NAME_LENGTH_MESSAGE = "Last name cannot exceed 50 characters";
    public static final String BIO_LENGTH_MESSAGE = "Bio cannot exceed 500 characters";
    public static final String LANGUAGE_LENGTH_MESSAGE = "Language cannot exceed 100 characters";
    public static final String STATUS_LENGTH_MESSAGE = "Status cannot exceed 100 characters";
    public static final String CONFIRMATION_TEXT_LENGTH_MESSAGE = "Confirmation text must not exceed " + CONFIRMATION_TEXT_MAX_LENGTH + CHARACTERS_SUFFIX;
    public static final String STATUS_DESCRIPTION_LENGTH_MESSAGE = "Status description cannot exceed " + STATUS_DESCRIPTION_MAX_LENGTH + CHARACTERS_SUFFIX;

    public static final String FIRST_NAME_REQUIRED = FIRST_NAME + " " + REQUIRED_FIELD;
    public static final String EMAIL_REQUIRED = EMAIL + " " + REQUIRED_FIELD;
    public static final String EMAIL_INVALID = EMAIL + " " + INVALID_FORMAT;
} 