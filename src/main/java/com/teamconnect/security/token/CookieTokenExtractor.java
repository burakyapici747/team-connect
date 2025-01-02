package com.teamconnect.security.token;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

/**
 * Extracts JWT token from cookies
 */
public class CookieTokenExtractor implements TokenExtractor {
    private static final String JWT_COOKIE_NAME = "jwt";

    @Override
    public Optional<String> extract(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
            .filter(cookie -> JWT_COOKIE_NAME.equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst();
    }
} 