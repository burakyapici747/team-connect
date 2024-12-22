package com.teamconnect.common.enumarator;

public enum MessageTargetType {
    USER("USER"),
    TEAM("TEAM");

    private final String value;

    MessageTargetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
