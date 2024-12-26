package com.teamconnect.api.output.user;

public record UserDetailsPrivateOutput(
    String id,
    String email,
    String name,
    String lastName
) {}
