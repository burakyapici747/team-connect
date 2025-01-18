package com.teamconnect.filter;

import com.teamconnect.common.constant.SecurityConstants;
import com.teamconnect.security.token.BearerTokenExtractor;
import com.teamconnect.security.token.CookieTokenExtractor;
import com.teamconnect.security.token.TokenExtractor;
import com.teamconnect.service.impl.CustomUserDetailsService;
import com.teamconnect.service.impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final List<TokenExtractor> tokenExtractors;

    public CustomAuthorizationFilter(JWTService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.tokenExtractors = initializeTokenExtractors();
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            skipAuthorizationIfPublic(request, response, filterChain);

            processSecuredEndpoint(request);
        } catch (SecurityException e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private void skipAuthorizationIfPublic(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        if (SecurityConstants.Endpoints.Public.matches(request.getMethod(), request.getRequestURI())) {
            filterChain.doFilter(request, response);
        }
    }

    private void processSecuredEndpoint(HttpServletRequest request) {
        String token = extractToken(request);
        validateToken(token);
        authenticateUser(token);
    }

    private String extractToken(HttpServletRequest request) {
        return tokenExtractors.stream()
            .map(extractor -> extractor.extract(request))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst()
            .orElseThrow(() -> new SecurityException("No valid token found"));
    }

    private void validateToken(String token) {
        if (!jwtService.isTokenValid(token)) {
            throw new SecurityException("Invalid token");
        }
    }

    private void authenticateUser(String token) {
        String email = jwtService.extractUsername(token);
        validateEmailAndSecurityContext(email);
        setAuthentication(email);
    }

    private void validateEmailAndSecurityContext(String email) {
        if (Objects.isNull(email) || Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            throw new SecurityException("Invalid token");
        }
    }

    private void setAuthentication(String email) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private List<TokenExtractor> initializeTokenExtractors() {
        return Arrays.asList(
            new CookieTokenExtractor(),
            new BearerTokenExtractor()
        );
    }
}
