package com.teamconnect.common.enumarator;

public enum MeetingRole {
    ORGANIZER("ORGANIZER"),
    PARTICIPANT("PARTICIPANT");

    private final String role;

    MeetingRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
