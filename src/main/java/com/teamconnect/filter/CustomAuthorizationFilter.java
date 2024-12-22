package com.teamconnect.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.teamconnect.service.impl.CustomUserDetailsService;
import com.teamconnect.service.impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private static final List<String> PUBLIC_ENDPOINTS = List.of(
        "/v1/api/users/register",
        "/v1/api/users/login",
        "/v1/api/auth/refresh-token"
    );
    private final JWTService jwtService;
    private static final String BEARER_PREFIX = "Bearer ";
    private final CustomUserDetailsService customUserDetailsService;
    private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();
    
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            if (shouldSkipAuthorization(request)) {
                proceedWithFilterChain(filterChain, request, response);
                return;
            }

            String token = extractTokenFromRequest(request);
            if (token == null) {
                proceedWithFilterChain(filterChain, request, response);
                return;
            }
            processToken(token);
        } catch (Exception e) {
            log.error("Authorization error occurred: ", e);
            SecurityContextHolder.clearContext();
        }

        proceedWithFilterChain(filterChain, request, response);
    }

    private boolean shouldSkipAuthorization(HttpServletRequest request) {
        return isPublicEndpoint(request.getRequestURI()) || 
               isPreflightRequest(request);
    }

    private boolean isPublicEndpoint(String requestURI) {
        return PUBLIC_ENDPOINTS.stream()
            .anyMatch(requestURI::contains);
    }

    private boolean isPreflightRequest(HttpServletRequest request) {
        return "OPTIONS".equals(request.getMethod());
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            return null;
        }

        return authHeader.substring(BEARER_PREFIX_LENGTH);
    }

    private void processToken(String token) {
        String userId = jwtService.extractUserId(token);
        if (isValidTokenContext(userId)) {
            authenticateUser(token, userId);
        }
    }

    private boolean isValidTokenContext(String userId) {
        return userId != null && 
               SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void authenticateUser(String token, String email) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        
        if (jwtService.isTokenValid(token, userDetails)) {
            setSecurityContext(userDetails);
        }
    }

    private void setSecurityContext(UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private void proceedWithFilterChain(
            FilterChain filterChain, 
            HttpServletRequest request, 
            HttpServletResponse response) throws IOException, ServletException {
        filterChain.doFilter(request, response);
    }
}
