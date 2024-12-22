package com.teamconnect.common.enumarator;

public enum FileType {
    IMAGE("image"),
    VIDEO("video"),
    AUDIO("audio"),
    DOCUMENT("document"),
    OTHER("other");

    private final String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FileType fromValue(String value) {
        for (FileType fileType : FileType.values()) {
            if (fileType.value.equals(value)) {
                return fileType;
            }
        }
        return OTHER;
    }
}
