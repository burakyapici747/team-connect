package com.teamconnect.common.enumarator;

public enum FilePurposeType {
    AVATAR("AVATAR"),
    ATTACHMENT("ATTACHMENT"),
    BACKGROUND("BACKGROUND"),
    LOGO("LOGO"),
    IMAGE("IMAGE");

    private final String value;

    FilePurposeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
