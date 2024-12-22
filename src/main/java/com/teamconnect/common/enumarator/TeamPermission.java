package com.teamconnect.common.enumarator;

public enum TeamPermission {
    DELETE("team.delete");

    private final String permission;

    TeamPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
