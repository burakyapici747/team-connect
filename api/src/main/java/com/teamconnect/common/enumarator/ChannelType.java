package com.teamconnect.common.enumarator;

import lombok.Getter;

@Getter
public enum ChannelType {
    GUILD_TEXT(0),
    DM(1),
    GUILD_VOICE(2),
    GROUP_DM(3),
    GUILD_CATEGORY(4),
    GUILD_ANNOUNCEMENT(5),
    ANNOUNCEMENT_THREAD(10),
    PUBLIC_THREAD(11),
    PRIVATE_THREAD(12),
    GUILD_STAGE_VOICE(13),
    GUILD_DIRECTORY(14),
    GUILD_FORUM(15),
    GUILD_MEDIA(16),
    UNKNOWN(-1);

    private final int code;

    ChannelType(int code) {
        this.code = code;
    }

    public static ChannelType fromCode(int code) {
        for (ChannelType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
