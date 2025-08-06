package com.teamconnect.api.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record AttachmentInput(
    UUID id,
    String filename,
    String description,
    String contentType,
    Long size,
    String url,

    @JsonProperty("proxy_url")
    String proxyUrl,

    Integer height,
    Integer width,
    boolean ephemeral
) {}
