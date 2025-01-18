package com.teamconnect.common.enumarator;

public enum FilePurposeType {
    AVATAR("avatar"),
    ATTACHMENT("attachment"),
    BACKGROUND("background"),
    LOGO("logo"),
    IMAGE("image");

    private final String value;

    FilePurposeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
