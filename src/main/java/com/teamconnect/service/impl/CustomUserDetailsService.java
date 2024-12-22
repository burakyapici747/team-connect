package com.teamconnect.service.impl;

import com.teamconnect.dto.UserDto;
import com.teamconnect.model.sql.CustomUserDetails;
import com.teamconnect.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.getUserByEmail(username);
        return new CustomUserDetails(userDto.id(), userDto.email(), userDto.password(), userDto.authorities());
    }
}
