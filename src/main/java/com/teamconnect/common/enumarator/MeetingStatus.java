package com.teamconnect.common.enumarator;

public enum MeetingStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    DECLINED("DECLINED");

    private final String status;

    MeetingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
