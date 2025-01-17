package com.teamconnect.api.input.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MessageCreateInput(
    @NotBlank(message = "Content is required")
    @Size(max = 2000, message = "Content cannot exceed 2000 characters")
    String content,

    @NotBlank(message = "Channel ID is required")
    String channelId,

    String parentMessageId
) {}
