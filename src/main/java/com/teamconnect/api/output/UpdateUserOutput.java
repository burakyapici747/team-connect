package com.teamconnect.api.output;

public record UpdateUserOutput(
    String id,
    String email,
    String name,
    String lastName
) {} 