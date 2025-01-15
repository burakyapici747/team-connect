package com.teamconnect.common.enumarator;

public enum FriendshipStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String value;

    FriendshipStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}