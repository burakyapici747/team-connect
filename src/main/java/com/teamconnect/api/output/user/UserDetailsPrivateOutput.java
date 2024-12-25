package com.teamconnect.api.output.user;

public record UserDetailsPrivateOutput(
    String id,
    String name,
    String lastName,
    String email
) {}
