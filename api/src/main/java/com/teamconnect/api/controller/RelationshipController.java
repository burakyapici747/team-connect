package com.teamconnect.api.controller;

import com.teamconnect.api.input.relationship.FriendRequestSendInput;
import com.teamconnect.api.output.ResponseWrapper;
import java.util.UUID;

import com.teamconnect.dto.RelationshipDto;
import com.teamconnect.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users/@me/relationships")
public class RelationshipController {
    @GetMapping
    public ResponseEntity<ResponseWrapper<RelationshipDto>> getRelationships(){
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<RelationshipDto>> sendFriendRequest(
        @RequestBody FriendRequestSendInput input,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> sendOrAcceptFriendRequest(
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> removeRelationship(
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }
}
