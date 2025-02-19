package com.teamconnect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSocketSecurityConfig {
    @Bean
    public SecurityFilterChain webSocketFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(AntPathRequestMatcher.antMatcher("/ws/**"))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(
                session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/ws/**", "/stomp/**").permitAll()
            )
            .build();
    }
}
