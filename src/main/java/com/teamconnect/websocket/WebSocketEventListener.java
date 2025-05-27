package com.teamconnect.websocket;

import com.teamconnect.security.CustomUserDetails;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        CustomUserDetails userDetails = extractUserDetails(event);
        if (userDetails != null) {
            System.out.println("User connected: " + userDetails.getUsername());
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        CustomUserDetails userDetails = extractUserDetails(event);
        if (userDetails != null) {
            System.out.println("User disconnected: " + userDetails.getUsername());
        }
    }

    private CustomUserDetails extractUserDetails(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        return extractUserDetails(headerAccessor);
    }

    private CustomUserDetails extractUserDetails(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        return extractUserDetails(headerAccessor);
    }

    private CustomUserDetails extractUserDetails(StompHeaderAccessor headerAccessor) {
        UsernamePasswordAuthenticationToken authentication =
            (UsernamePasswordAuthenticationToken) headerAccessor.getUser();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }

        return null;
    }
}
