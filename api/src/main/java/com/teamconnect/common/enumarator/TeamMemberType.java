package com.teamconnect.common.enumarator;

public enum TeamMemberType {
    CREATOR("CREATOR"),
    MEMBER("MEMBER");

    private final String value;

    TeamMemberType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
