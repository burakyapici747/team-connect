package com.teamconnect.dto;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record MessageDto(
    UUID id,
    UUID channelId,
    UserDto author,
    String content,
    Instant timestamp,
    Instant editedTimestamp,
    boolean mentionEveryone,
    Set<UserDto> mentions,
    Set<AttachmentDto> attachments,
    List<ReactionDto> reactions,
    boolean pinned,
    Integer type
) {}


