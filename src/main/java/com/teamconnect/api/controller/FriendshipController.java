package com.teamconnect.api.controller;

import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.friendship.FriendshipOutput;
import java.util.List;

import com.teamconnect.mapper.FriendshipMapper;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/users/{userId}/friendships")
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final FriendshipMapper friendshipMapper;

    public FriendshipController(
        FriendshipService friendshipService,
        FriendshipMapper friendshipMapper
    ) {
        this.friendshipService = friendshipService;
        this.friendshipMapper = friendshipMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<FriendshipOutput>>> getFriendships(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseWrapper.ok(
            friendshipMapper.friendshipDtoListToFriendshipOutputList(
                friendshipService.getAllFriendships(userDetails.getId())
            )
        );
    }

    @GetMapping("/incoming-requests")
    public ResponseEntity<ResponseWrapper<List<FriendshipOutput>>> getIncomingFriendRequests(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseWrapper.ok(
            friendshipMapper.friendshipDtoListToFriendshipOutputList(
                friendshipService.getIncomingFriendRequests(userDetails.getId())
            )
        );
    }

    @GetMapping("/outgoing-requests")
    public ResponseEntity<ResponseWrapper<List<FriendshipOutput>>> getOutgoingFriendRequests(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ResponseWrapper.ok(
            friendshipMapper.friendshipDtoListToFriendshipOutputList(
                friendshipService.getOutgoingFriendRequests(userDetails.getId())
            )
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<FriendshipOutput>> sendFriendRequest(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @PathVariable String friendId
    ) {
        return ResponseWrapper.ok(
            friendshipMapper.friendshipDtoToFriendshipOutput(
                friendshipService.sendFriendRequest(userDetails.getId(), friendId)
            )
        );
    }
}
