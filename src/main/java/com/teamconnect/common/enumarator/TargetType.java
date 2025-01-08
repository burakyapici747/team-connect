package com.teamconnect.common.enumarator;

public enum TargetType {
    USER("USER"),
    ROLE("ROLE");

    private final String value;

    TargetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
