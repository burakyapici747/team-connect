package com.teamconnect.api.input.channel;

import com.teamconnect.common.enumarator.ChannelType;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record ChannelCreateInput (
    @NotBlank(message = "ChannelType is not be null")
    ChannelType channelType,
    String name,
    String teamId,
    Integer position,
    Integer permissionOverwrites,
    Integer useLimit,
    Set<String> recipients
){}
