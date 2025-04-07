package com.teamconnect.api.output.channel;

import com.teamconnect.common.enumarator.ChannelType;

import java.util.Set;

public record ChannelPrivateOutput(
    String id,
    ChannelType channelType,
    String teamId,
    int position,
    int permission_overwrites,
    String name,
    String lastMessageId,
    int userLimit,
    Set<String> recipients,
    String ownerId
){}
