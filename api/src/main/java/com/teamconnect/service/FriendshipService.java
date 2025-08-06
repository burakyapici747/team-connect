package com.teamconnect.service;

import com.teamconnect.dto.FriendshipDto;
import java.util.List;

public interface FriendshipService {
    List<FriendshipDto> getAllFriendships(String currentUserId);
    List<FriendshipDto> getOutgoingFriendRequests(String currentUserId);
    List<FriendshipDto> getIncomingFriendRequests(String currentUserId);
    List<FriendshipDto> approveFriendRequest(String currentUserId, String friendId);
    FriendshipDto sendFriendRequest(String currentUserId, String targetUserId);
    void rejectFriendRequest(String currentUserId, String friendId);
}
