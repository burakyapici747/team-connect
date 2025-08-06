package com.teamconnect.common.enumarator;

import lombok.Getter;

@Getter
public enum MfaLevel {
    NONE(0, "guild has no MFA/2FA requirement for moderation actions"),
    ELEVATED(1, "guild has a 2FA requirement for moderation actions");

    private final int value;
    private final String description;

    MfaLevel(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
