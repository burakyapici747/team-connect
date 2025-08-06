package com.teamconnect.dto;

import com.teamconnect.common.enumarator.FileType;

import java.util.UUID;

public record AttachmentDto(
     UUID id,
     String fileName,
     String title,
     String description,
     String contentType,
     Integer size,
     String url,
     Integer height,
     Integer width,
     Boolean ephemeral,
     Integer durationSecs,
     String waveform,
     Integer flags,
     FileType type
) {}
