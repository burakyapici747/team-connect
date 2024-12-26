package com.teamconnect.api.output.user;

public record UserDetailsPublicOutput(
    String id,
    String email,
    String name,
    String lastName
) {}
