package com.teamconnect.api.input.message;

import com.teamconnect.common.enumarator.MessageTargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record MessageCreateInput(
    @NotBlank(message = "Content is required")
    @Size(max = 2000, message = "Content cannot exceed 2000 characters")
    String content,

    @NotNull(message = "Target type is required")
    MessageTargetType messageTargetType,

    @NotBlank(message = "Receiver ID is required")
    String receiverId,

    String parentMessageId,

    List<String> fileIds
) {} 