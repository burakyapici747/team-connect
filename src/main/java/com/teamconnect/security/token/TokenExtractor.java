package com.teamconnect.security.token;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Strategy interface for extracting JWT tokens from different sources in the request
 */
public interface TokenExtractor {
    Optional<String> extract(HttpServletRequest request);
} 