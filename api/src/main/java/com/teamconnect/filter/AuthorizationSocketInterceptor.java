package com.teamconnect.filter;

import com.teamconnect.service.impl.JWTService;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationSocketInterceptor implements ChannelInterceptor {

    private final JWTService jwtService;
    private static final String BEARER_PREFIX = "Bearer ";

    public AuthorizationSocketInterceptor(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        final StompHeaderAccessor accessor = getStompAccessor(message);
        
        if (isConnectCommand(accessor)) {
            authenticateConnection(accessor);
        }
        
        return message;
    }

    private StompHeaderAccessor getStompAccessor(Message<?> message) {
        return MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    }

    private boolean isConnectCommand(StompHeaderAccessor accessor) {
        return StompCommand.CONNECT.equals(accessor.getCommand());
    }

    private void authenticateConnection(StompHeaderAccessor accessor) {
        final String token = extractTokenFromHeader(accessor);
        validateToken(token);
        setAuthentication(accessor, token);
    }

    private String extractTokenFromHeader(StompHeaderAccessor accessor) {
        final String authorization = accessor.getFirstNativeHeader("Authorization");
        
        if (authorization == null || !authorization.startsWith(BEARER_PREFIX)) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }
        
        return authorization.substring(BEARER_PREFIX.length());
    }

    private void validateToken(String token) {
        if (!jwtService.isTokenValid(token)) {
            throw new IllegalArgumentException("Invalid JWT token");
        }
    }

    private void setAuthentication(StompHeaderAccessor accessor, String token) {
        final String username = jwtService.extractUsername(token);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        accessor.setUser(auth);
    }
}
