package com.teamconnect.api.output.user;

public record RegisterOutput(
    String id,
    String email,
    String name,
    String lastName
) {}
