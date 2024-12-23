package com.teamconnect.api.output;

public record GetUserOutput(
    String id,
    String email,
    String name,
    String lastName
) {} 