package com.teamconnect.api.controller;

import com.teamconnect.model.websocket.WebSocketMessage;
import com.teamconnect.model.websocket.WebSocketMessageStatus;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.WebSocketMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketMessageController {
    private final WebSocketMessageService webSocketMessageService;

    public WebSocketMessageController(WebSocketMessageService webSocketMessageService) {
        this.webSocketMessageService = webSocketMessageService;
    }

    @MessageMapping("/message.send")
    public void handlePrivateMessage(
        @Payload WebSocketMessage message,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
//        webSocketMessageService.sendPrivateMessage(message, userDetails.getId());
    }

    @MessageMapping("/message.status")
    public void handleMessageStatus(
        @Payload WebSocketMessageStatus status,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        webSocketMessageService.updateMessageStatus(status, userDetails.getId());
    }
}
