package com.teamconnect.common.enumarator;

public enum ChannelType {
    TEXT_CHANNEL("TEXT_CHANNEL"),
    VOICE_CHANNEL("VOICE_CHANNEL"),
    DIRECT_CHANNEL("DIRECT_CHANNEL");

    private final String value;

    ChannelType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
