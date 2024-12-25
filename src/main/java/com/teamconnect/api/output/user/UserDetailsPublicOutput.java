package com.teamconnect.api.output.user;

public record UserDetailsPublicOutput(
    String id,
    String name,
    String lastName,
    String email
) {}
