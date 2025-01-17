package com.teamconnect.api.output.channel;

import java.util.List;

public record ChannelPublicOutput (
    String id,
    String name,
    String topic,
    String lastMessageId,
    Integer userLimit,
    Integer rateLimitPerUser,
    List<String> recipients,
    String icon,
    String ownerId
){}
