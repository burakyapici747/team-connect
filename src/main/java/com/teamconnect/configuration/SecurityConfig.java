package com.teamconnect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamconnect.exception.CustomAuthenticationFailureHandler;
import com.teamconnect.filter.CustomAuthenticationFilter;
import com.teamconnect.filter.CustomAuthorizationFilter;
import com.teamconnect.security.CustomAuthenticationProvider;
import com.teamconnect.service.impl.JWTService;

import jakarta.validation.Validator;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomAuthorizationFilter customAuthorizationFilter;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public SecurityConfig(
            CustomAuthenticationProvider customAuthenticationProvider,
            CustomAuthorizationFilter customAuthorizationFilter,
            CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customAuthorizationFilter = customAuthorizationFilter;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(
        HttpSecurity http,
        CustomAuthenticationFilter customAuthenticationFilter) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(customAuthenticationProvider)
            .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(customAuthorizationFilter, CustomAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll())
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(
            AuthenticationManager authenticationManager,
            Validator validator,
            JWTService jwtService,
            ObjectMapper objectMapper) {
        return new CustomAuthenticationFilter(
                authenticationManager,
                validator,
                jwtService,
                objectMapper,
                customAuthenticationFailureHandler);
    }
}
