package com.teamconnect.api.controller;

import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/channels/{channelId}/pins")
public class PinController {

    @GetMapping
    public ResponseEntity<ResponseWrapper<Void>> getPins(
        @PathVariable("channelId") UUID channelId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PutMapping(path = "/{channelId}/pins/{messageId}")
    public ResponseEntity<ResponseWrapper<Void>> pinMessage(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @DeleteMapping(path = "/{channelId}/pins/{messageId}")
    public ResponseEntity<ResponseWrapper<Void>> unpinMessage(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }
}
