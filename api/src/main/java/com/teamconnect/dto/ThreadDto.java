package com.teamconnect.dto;

import java.time.Instant;
import java.util.UUID;

public record ThreadDto(
    UUID id,
    String name,
    Integer type,
    boolean archived,
    Integer autoArchiveDuration,
    Instant archiveTimestamp
) {}
