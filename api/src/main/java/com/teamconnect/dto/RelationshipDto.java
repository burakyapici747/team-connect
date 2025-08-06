package com.teamconnect.dto;

import com.teamconnect.common.enumarator.RelationshipType;

import java.util.UUID;

public record RelationshipDto(
    UUID id,
    RelationshipType type,
    String nickname,
    UserDto user
) {}
