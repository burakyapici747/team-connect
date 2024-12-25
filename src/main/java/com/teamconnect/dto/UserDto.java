package com.teamconnect.dto;

import java.util.Set;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public record UserDto(
        String id,
        String name,
        String lastName,
        String email,
        String password,
        Set<SimpleGrantedAuthority> authorities
){}
