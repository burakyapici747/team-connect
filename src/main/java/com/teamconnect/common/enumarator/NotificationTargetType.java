package com.teamconnect.common.enumarator;

public enum NotificationTargetType {
    USER("USER"),
    TEAM("TEAM"),
    MESSAGE("MESSAGE"),
    MEETING("MEETING"),
    MENTION("MENTION"),;

    private final String value;

    NotificationTargetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
