package com.teamconnect.api.output.friendship;

import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.common.enumarator.FriendshipStatus;
import java.time.Instant;

public record FriendshipOutput (
    UserDetailsPublicOutput currentUser,
    UserDetailsPublicOutput otherUser,
    FriendshipStatus status,
    Instant createdAt,
    Instant updatedAt
){}
