package com.teamconnect.api.output;

public record LoginOutput(
    String id,
    String email,
    String name,
    String lastName,
    String token
) {} 