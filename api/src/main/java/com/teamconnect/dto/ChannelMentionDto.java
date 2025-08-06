package com.teamconnect.dto;

import java.util.UUID;

public record ChannelMentionDto(
    UUID id,
    String name,
    Integer type
) {}

