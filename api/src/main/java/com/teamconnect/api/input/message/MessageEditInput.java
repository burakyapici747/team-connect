package com.teamconnect.api.input.message;

import jakarta.validation.constraints.Size;

public record MessageEditInput(
    @Size(max = 2000, message = "Content cannot exceed 2000 characters")
    String content,

    boolean isPinned
) {}
