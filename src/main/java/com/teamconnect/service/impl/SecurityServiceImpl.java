package com.teamconnect.service.impl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.teamconnect.exception.UnauthorizedAccessException;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.SecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final AuthenticationManager authenticationManager;

    @Override
    public void validateUserAccess(String userId) {
        CustomUserDetails currentUser = getCurrentUser();
        if (!currentUser.getId().equals(userId)) {
            throw new AccessDeniedException("You can only access your own information");
        }
    }

    @Override
    public void validateSecureOperation(String userId, String password) {
        validateUserAccess(userId);

        try {
            String email = getCurrentUser().getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            throw new UnauthorizedAccessException("Invalid password for secure operation");
        }
    }

    @Override
    public CustomUserDetails getCurrentUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        return (CustomUserDetails) authentication.getPrincipal();
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
