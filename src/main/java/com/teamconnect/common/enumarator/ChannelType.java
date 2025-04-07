package com.teamconnect.common.enumarator;

import lombok.Getter;

@Getter
public enum ChannelType {
    TEAM_TEXT(0),
    DM(1),
    TEAM_VOICE(2),
    GROUP_DM(3),
    TEAM_CATEGORY(4);

    private final int id;

    ChannelType(int id){
        this.id = id;
    }
}
