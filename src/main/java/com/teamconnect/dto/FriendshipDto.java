package com.teamconnect.dto;

import com.teamconnect.common.enumarator.FriendshipStatus;

import java.time.Instant;

public record FriendshipDto(
    UserDto currentUser,
    UserDto otherUser,
    FriendshipStatus status,
    Instant createdAt,
    Instant updatedAt
){}
