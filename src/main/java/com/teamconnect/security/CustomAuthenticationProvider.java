package com.teamconnect.security;

import com.teamconnect.exception.UserNotFoundException;
import com.teamconnect.service.impl.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(
        CustomUserDetailsService customUserDetailsService,
        PasswordEncoder passwordEncoder
    ) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
            validatePassword(userDetails, (UsernamePasswordAuthenticationToken) authentication);
            return new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
            );
        }catch (Exception e){
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private void validatePassword(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authenticationToken
    ) {
        if(!passwordEncoder.matches(authenticationToken.getCredentials().toString(), userDetails.getPassword())){
            throw new UserNotFoundException("User not found");
        }
    }
}
