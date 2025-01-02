package com.teamconnect.websocket;

import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.WebSocketMessageService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final WebSocketMessageService webSocketMessageService;

    public WebSocketEventListener(WebSocketMessageService webSocketMessageService) {
        this.webSocketMessageService = webSocketMessageService;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        CustomUserDetails userDetails = extractUserDetails(event);
        if (userDetails != null) {
            webSocketMessageService.handleUserStatusChange(userDetails.getId(), true);
            webSocketMessageService.handleOfflineMessages(userDetails.getId());
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        CustomUserDetails userDetails = extractUserDetails(event);
        if (userDetails != null) {
            webSocketMessageService.handleUserStatusChange(userDetails.getId(), false);
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