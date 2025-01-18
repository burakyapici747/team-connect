package com.teamconnect.service.impl;

import com.teamconnect.common.enumarator.FriendshipStatus;
import com.teamconnect.dto.FriendshipDto;
import com.teamconnect.mapper.FriendshipMapper;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.model.sql.Friendship;
import com.teamconnect.repository.postgresql.FriendshipRepository;
import com.teamconnect.service.FriendshipService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public FriendshipServiceImpl(
        UserMapper userMapper,
        FriendshipMapper friendshipMapper,
        FriendshipRepository friendshipRepository,
        UserServiceImpl userService
    ) {
        this.userMapper = userMapper;
        this.friendshipMapper = friendshipMapper;
        this.friendshipRepository = friendshipRepository;
        this.userService = userService;
    }

    @Override
    public List<FriendshipDto> getAllFriendships(String currentUserId) {
        return getFriendshipDtos(friendshipRepository.findAllFriendshipsByUserId(currentUserId), currentUserId);
    }

    @Override
    public List<FriendshipDto> getOutgoingFriendRequests(String currentUserId) {
        return getFriendshipDtos(friendshipRepository.findOutgoingFriendRequestsByUserId(currentUserId), currentUserId);
    }

    @Override
    public List<FriendshipDto> getIncomingFriendRequests(String currentUserId) {
        return getFriendshipDtos(friendshipRepository.findIncomingFriendRequestsByUserId(currentUserId), currentUserId);
    }

    @Override
    public List<FriendshipDto> approveFriendRequest(String currentUserId, String friendId) {
        Friendship friendship = findFriendshipById(friendId);

        validateFriendshipDoesNotExist(currentUserId, friendship);
        validateCurrentUserIsReceiver(currentUserId, friendship);

        friendship.setStatus(FriendshipStatus.FRIEND);
        friendshipRepository.save(friendship);

        return getAllFriendships(currentUserId);
    }

    @Override
    public FriendshipDto sendFriendRequest(String currentUserId, String targetUserId) {
        validateIsNotSelf(currentUserId, targetUserId);
        validateFriendship(currentUserId, targetUserId);
        Friendship friendship = createFriendship(currentUserId, targetUserId);

        friendshipRepository.save(friendship);

        return friendshipMapper.friendshipToFriendshipDto(friendship, currentUserId, userMapper);
    }

    @Override
    public void rejectFriendRequest(String currentUserId, String friendId) {
        Friendship friendship = findFriendshipById(friendId);

        validateFriendshipDoesNotExist(currentUserId, friendship);
        validateCurrentUserIsReceiver(currentUserId, friendship);

        friendshipRepository.delete(friendship);
    }

    private void validateIsNotSelf(String currentUserId, String friendId) {
        if (currentUserId.equals(friendId)) {
            throw new RuntimeException("Current user cannot send friend request to themselves");
        }
    }

    private void validateCurrentUserIsReceiver(String currentUserId, Friendship friendship) {
        if( (friendship.getUser1().getId().equals(currentUserId)) && (friendship.getStatus() == FriendshipStatus.REQ_UID2) ||
            (friendship.getUser2().getId().equals(currentUserId)) && (friendship.getStatus() == FriendshipStatus.REQ_UID1)){
            throw new RuntimeException("Current user is not the receiver of the friendship request");
        }
    }

    private void validateFriendshipDoesNotExist(String currentUserId, Friendship friendship) {
        if (friendship.getStatus() == FriendshipStatus.FRIEND) {
            throw new RuntimeException("Friendship already exists between users");
        }
    }

    private List<FriendshipDto> getFriendshipDtos(List<Friendship> friendships, String currentUserId) {
        return friendships.stream()
            .map(friendship -> friendshipMapper.friendshipToFriendshipDto(friendship, currentUserId, userMapper))
            .toList();
    }

    private Friendship findFriendshipById(String friendshipId) {
        return friendshipRepository.findById(friendshipId)
            .orElseThrow(() -> new RuntimeException("Friendship not found"));
    }

    private String getOtherUserId(Friendship friendship, String currentUserId) {
        return friendship.getUser1().getId().equals(currentUserId) ? friendship.getUser2().getId() : friendship.getUser1().getId();
    }

    private void validateFriendship(String currentUserId, String targetUserId) {
        Friendship friendship = friendshipRepository.findFriendshipBetweenUsers(currentUserId, targetUserId);

        if (friendship != null) {
            if (friendship.getStatus() == FriendshipStatus.FRIEND) {
                throw new RuntimeException("Users are already friends");
            }

            if ((friendship.getUser1().getId().equals(currentUserId) && friendship.getStatus() == FriendshipStatus.REQ_UID1) ||
                (friendship.getUser2().getId().equals(currentUserId) && friendship.getStatus() == FriendshipStatus.REQ_UID2)) {
                throw new RuntimeException("Friend request already sent");
            }

            if ((friendship.getUser1().getId().equals(currentUserId) && friendship.getStatus() == FriendshipStatus.REQ_UID2) ||
                (friendship.getUser2().getId().equals(currentUserId) && friendship.getStatus() == FriendshipStatus.REQ_UID1)) {
                throw new RuntimeException("Friend request already received from this user");
            }
        }
    }

    private Friendship createFriendship(String currentUserId, String targetUserId) {
        Friendship friendship = new Friendship();

        if (currentUserId.compareTo(targetUserId) < 0) {
            friendship.setUser1(userService.getUserEntityById(currentUserId));
            friendship.setUser2(userService.getUserEntityById(targetUserId));
        } else {
            friendship.setUser1(userService.getUserEntityById(targetUserId));
            friendship.setUser2(userService.getUserEntityById(currentUserId));
        }

        friendship.setStatus(currentUserId.equals(friendship.getUser1().getId()) ?
            FriendshipStatus.REQ_UID1 : FriendshipStatus.REQ_UID2);

        return friendship;
    }
}
