package com.teamconnect.api.controller;

import com.teamconnect.api.input.invite.InviteCreateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/channels/{channelId}/invites")
public class InviteController {
    @GetMapping
    public ResponseEntity<ResponseWrapper<Void>> getInvites(
        @PathVariable("channelId") UUID channelId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Void>> createInvite(
        @PathVariable("channelId") UUID channelId,
        @RequestBody InviteCreateInput inviteCreateInput,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @DeleteMapping(path = "/{inviteCode}")
    public ResponseEntity<ResponseWrapper<Void>> deleteInvite(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("inviteCode") String inviteCode,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }
}
