package com.teamconnect.common.enumarator;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

@Getter
public enum SystemChannelFlag {
    SUPPRESS_JOIN_NOTIFICATIONS(1),

    SUPPRESS_PREMIUM_SUBSCRIPTIONS(1 << 1),

    SUPPRESS_GUILD_REMINDER_NOTIFICATIONS(1 << 2);

    private final int bit;

    SystemChannelFlag(int bit) {
        this.bit = bit;
    }

    public static EnumSet<SystemChannelFlag> fromInt(int raw) {
        EnumSet<SystemChannelFlag> result = EnumSet.noneOf(SystemChannelFlag.class);
        for (SystemChannelFlag flag : values()) {
            if ((raw & flag.bit) == flag.bit) {
                result.add(flag);
            }
        }
        return result;
    }

    public static int toInt(Set<SystemChannelFlag> flags) {
        int raw = 0;
        for (SystemChannelFlag flag : flags) {
            raw |= flag.bit;
        }
        return raw;
    }
}

