package com.teamconnect.common.enumarator;

public enum ChatType {
    TEAM_TEXT_CHANNEL("TEAM_TEXT_CHANNEL"),
    TEAM_VOICE_CHANNEL("TEAM_VOICE_CHANNEL"),
    DIRECT("DIRECT"),
    GROUP("GROUP");

    private final String value;

    ChatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
