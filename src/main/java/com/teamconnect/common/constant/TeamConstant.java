package com.teamconnect.common.constant;

public final class TeamConstant {
    private TeamConstant() {
        throw new IllegalStateException("Constant class");
    }

    // Success Messages
    public static final String TEAM_CREATED = "Team created successfully";
    public static final String TEAM_UPDATED = "Team updated successfully";
    public static final String TEAM_DELETED = "Team deleted successfully";
    public static final String MEMBER_ADDED = "Member added successfully";
    public static final String MEMBER_REMOVED = "Member removed successfully";
    public static final String MEMBER_ROLE_UPDATED = "Member role updated successfully";

    // Error Messages
    public static final String TEAM_NOT_FOUND = "Team not found: %s";
    public static final String TEAM_NAME_EXISTS = "Team name already exists: %s";
    public static final String MEMBER_NOT_FOUND = "Team membership not found";
    public static final String USER_NOT_FOUND = "User not found: %s";
    public static final String MEMBER_ALREADY_EXISTS = "User is already a member of the team";

    // Validation Messages
    public static final String NAME_NOT_EMPTY = "Team name cannot be empty";
    public static final String NAME_SIZE = "Team name must be between 3 and 50 characters";
    public static final String DESCRIPTION_SIZE = "Description cannot exceed 500 characters";
} 