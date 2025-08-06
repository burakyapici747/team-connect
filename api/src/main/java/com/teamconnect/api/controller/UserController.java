package com.teamconnect.api.controller;

import com.teamconnect.api.input.user.*;
import com.teamconnect.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.teamconnect.api.output.ResponseWrapper;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @GetMapping(path = "/@me")
    public ResponseEntity<ResponseWrapper<Void>> getCurrentUser(@AuthenticationPrincipal CustomUserDetails user){
        return null;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> getUser(
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PatchMapping(path = "/@me")
    public ResponseEntity<ResponseWrapper<Void>> modifyCurrentUser(
        @RequestBody UserUpdateInput userUpdateInput,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @GetMapping(path = "/@me/guilds")
    public ResponseEntity<ResponseWrapper<Void>> getCurrentUserGuilds(@AuthenticationPrincipal CustomUserDetails user){
        return null;
    }

    @GetMapping("/@me/guilds/{guildId}/member")
    public ResponseEntity<ResponseWrapper<Void>> getCurrentUserGuildMember(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @DeleteMapping(path = "/@me/guilds/{guildId}")
    public ResponseEntity<ResponseWrapper<Void>> leaveGuild(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PostMapping(path = "/@me/channels")
    public ResponseEntity<ResponseWrapper<Void>> createDMChannel(
        @RequestBody UserCreateDMChannelInput input,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PostMapping(path = "/@me/channels")
    public ResponseEntity<ResponseWrapper<Void>> createGroupDMChannel(
        @RequestBody UserCreateGroupDMChannelInput input,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }
}
