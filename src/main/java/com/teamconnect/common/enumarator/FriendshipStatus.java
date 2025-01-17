package com.teamconnect.common.enumarator;

public enum FriendshipStatus {
    REQ_UID1("REQ_UID1"),
    REQ_UID2("REQ_UID2"),
    FRIEND("FRIEND");

    private final String status;

    FriendshipStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
