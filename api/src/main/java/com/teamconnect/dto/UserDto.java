package com.teamconnect.dto;

import java.util.UUID;

public record UserDto(
    UUID id,
    String username,
    String discriminator,
    String globalName,
    String avatar,
    Boolean bot,
    Boolean system,
    Boolean mfaEnabled,
    String locale,
    Boolean verified,
    String email,
    Integer flags,
    Integer premiumType,
    Integer publicFlags
){}
