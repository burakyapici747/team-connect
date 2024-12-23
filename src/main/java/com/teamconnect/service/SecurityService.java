package com.teamconnect.service;

import org.springframework.security.core.Authentication;
import com.teamconnect.security.CustomUserDetails;

public interface SecurityService {
    void validateUserAccess(String userId);
    void validateSecureOperation(String userId, String password);
    CustomUserDetails getCurrentUser();
    Authentication getAuthentication();
} 