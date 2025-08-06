package com.teamconnect.common.enumarator;

public enum WebSocketMessageType {
    CHAT("CHAT"),
    FILE("FILE"),
    NOTIFICATION("NOTIFICATION"),
    STATUS_UPDATE("STATUS_UPDATE");

    private final String value;

    WebSocketMessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
} 