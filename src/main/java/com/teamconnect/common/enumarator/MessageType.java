package com.teamconnect.common.enumarator;

public enum MessageType {
    DEFAULT("DEFAULT"),
    SYSTEM("SYSTEM");

    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
