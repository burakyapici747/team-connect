package com.teamconnect.common.enumarator;

import lombok.Getter;

@Getter
public enum DefaultMessageNotificationLevel {
    ALL_MESSAGES(0, "members will receive notifications for all messages by default"),
    ONLY_MENTIONS(1, "members will receive notifications only for messages that @mention them by default");

    private final int value;
    private final String description;

    DefaultMessageNotificationLevel(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
