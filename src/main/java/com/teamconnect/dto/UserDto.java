package com.teamconnect.dto;

public record UserDto(
        String id,
        String email,
        String name,
        String lastName,
        String password
){}
