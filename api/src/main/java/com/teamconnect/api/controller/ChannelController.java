package com.teamconnect.api.controller;

import com.teamconnect.api.input.channel.ChannelCreateInput;
import com.teamconnect.api.input.channel.ChannelUpdateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.channel.ChannelPrivateOutput;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.ChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/channels")
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping(path = "{channelId}")
    public ResponseEntity<ResponseWrapper<ChannelDto>> getChannel(
        @PathVariable("channelId") UUID channelId
    ){
        return null;
    }

    @PatchMapping(path = "{channelId}")
    public ResponseEntity<ResponseWrapper<ChannelDto>> modifyChannel(
        @PathVariable("channelId") UUID channel,
        @RequestBody ChannelUpdateInput input,
        @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        return null;
    }

    @DeleteMapping(path = "{channelId}")
    public ResponseEntity<ResponseWrapper<Void>> closeChannel(
        @PathVariable("channelId") UUID channelId,
        @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<ChannelPrivateOutput>> createChannel(
        @RequestBody ChannelCreateInput input,
        @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        return null;
    }

    @PutMapping(path = "/{channelId}/recipients/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> addRecipient(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("userId") UUID  userId,
        @RequestBody Void recipientCreateBody,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return null;
    }

    @DeleteMapping(path = "/{channelId}/recipients/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> removeRecipient(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("userId") UUID  userId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return null;
    }
}
