package com.teamconnect.common.enumarator;

public enum UserStatus {
    ONLINE("ONLINE"),
    AWAY("AWAY"),
    BUSY("BUSY"),
    OFFLINE("OFFLINE");

    private final String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
