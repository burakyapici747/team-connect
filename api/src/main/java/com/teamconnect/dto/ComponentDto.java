package com.teamconnect.dto;

import java.util.List;

public record ComponentDto(
    Integer type,
    String customId,
    boolean disabled,
    Integer style,
    String label,
    EmojiDto emoji,
    String url,
    List<ComponentDto> components
) {}

