package com.teamconnect.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.teamconnect.common.constant.SecurityConstants;
import com.teamconnect.service.impl.CustomUserDetailsService;
import com.teamconnect.service.impl.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();

    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public CustomAuthorizationFilter(JWTService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            skipAuthorizationIfPublic(request, response, filterChain);

            String token = extractTokenFromRequest(request);
            validateToken(token);
            authenticateUserByEmail(token);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private void skipAuthorizationIfPublic(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        if (SecurityConstants.Endpoints.Public.matches(request.getMethod(), request.getRequestURI())) {
            filterChain.doFilter(request, response);
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            throw new SecurityException("Invalid token");
        }

        return authHeader.substring(BEARER_PREFIX_LENGTH);
    }

    private void validateToken(String token) {
        if (!jwtService.isTokenValid(token)) {
            throw new SecurityException("Invalid token");
        }
    }

    private void authenticateUserByEmail(String token) {
        String email = jwtService.extractUsername(token);

        validateEmailAndSecurityContext(email);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private void validateEmailAndSecurityContext(String email) {
        if (Objects.isNull(email) || Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            throw new SecurityException("Invalid token");
        }
    }
}
