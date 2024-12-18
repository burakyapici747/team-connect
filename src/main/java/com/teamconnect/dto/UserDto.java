package com.teamconnect.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserDto(
        String id,
        String email,
        String name,
        String lastName,
        String password,
        Collection<? extends GrantedAuthority> authorities
){}
