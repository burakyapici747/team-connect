package com.teamconnect.dto;

import java.util.UUID;

public record MessageReferenceDto(
    UUID messageId,
    UUID channelId,
    UUID guildId
) {}
