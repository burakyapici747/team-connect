package com.teamconnect.dto;

import java.util.List;

public record ChannelDto (
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
