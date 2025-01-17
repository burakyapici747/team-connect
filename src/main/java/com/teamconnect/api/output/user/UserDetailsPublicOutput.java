package com.teamconnect.api.output.user;

public record UserDetailsPublicOutput(
    String id,
    String email,
    String username,
    String profilePicture
) {}
