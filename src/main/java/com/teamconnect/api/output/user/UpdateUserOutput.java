package com.teamconnect.api.output.user;

public record UpdateUserOutput(
    String id,
    String email,
    String name,
    String lastName
) {}
