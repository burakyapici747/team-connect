package com.teamconnect.common.enumarator;

public enum MeetingCreatedType {
    CREATED_BY_USER("USER"),
    CREATED_BY_TEAM("TEAM");

    private final String value;

    MeetingCreatedType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
