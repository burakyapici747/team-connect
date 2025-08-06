package com.teamconnect.api.input.relationship;

public record FriendRequestSendInput(
    String username,
    Integer discriminator
) {}
