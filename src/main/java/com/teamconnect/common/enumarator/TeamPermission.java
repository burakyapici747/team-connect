package com.teamconnect.common.enumarator;

public enum TeamPermission {
    SEND_MESSAGE("SEND_MESSAGE"),
    DELETE_MESSAGE("DELETE_MESSAGE"),
    PIN_MESSAGE("PIN_MESSAGE"),
    UPLOAD_FILE("UPLOAD_FILE"),
    DELETE_FILE("DELETE_FILE"),
    UPDATE_TEAM_INFO("UPDATE_TEAM_INFO"),
    VIEW_ANALYTICS("VIEW_ANALYTICS"),
    MANAGE_CHANNELS("MANAGE_CHANNELS"),
    REMOVE_MEMBER("REMOVE_MEMBER"),
    ASSIGN_ROLE("ASSIGN_ROLE"),
    REMOVE_ROLE("REMOVE_ROLE"),
    ADD_MEMBER("ADD_MEMBER"),
    UPDATE_TEAM("UPDATE_TEAM"),
    CREATE_ROLE("CREATE_ROLE"),
    UPDATE_ROLE("UPDATE_ROLE");

    private final String value;

    TeamPermission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
