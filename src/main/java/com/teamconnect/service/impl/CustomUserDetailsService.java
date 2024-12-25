package com.teamconnect.service.impl;

import com.teamconnect.dto.UserDto;
import com.teamconnect.model.sql.CustomUserDetails;
import com.teamconnect.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userService.getUserByEmail(email);
        return new CustomUserDetails(
            userDto.email(),
            userDto.password(),
            userDto.authorities()
        );
    }
}
