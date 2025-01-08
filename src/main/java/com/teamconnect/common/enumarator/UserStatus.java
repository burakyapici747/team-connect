package com.teamconnect.common.enumarator;

public enum Availability {
    ONLINE("ONLINE"),
    AWAY("AWAY"),
    BUSY("BUSY"),
    OFFLINE("OFFLINE");

    private final String value;

    Availability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
