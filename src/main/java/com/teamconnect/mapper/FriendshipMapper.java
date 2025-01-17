package com.teamconnect.mapper;

import com.teamconnect.dto.FriendshipDto;
import com.teamconnect.model.sql.Friendship;
import com.teamconnect.model.sql.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.teamconnect.api.output.friendship.FriendshipOutput;
import java.util.List;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface FriendshipMapper {
    FriendshipMapper INSTANCE = Mappers.getMapper(FriendshipMapper.class);

    @Mapping(target = "status", source = "friendship.status")
    @Mapping(target = "updatedAt", source = "friendship.updatedAt")
    @Mapping(target = "createdAt", source = "friendship.createdAt")
    @Mapping(target = "otherUser", expression = "java(userMapper.userToUserDto(getOtherUser(friendship, currentUserId)))")
    @Mapping(target = "currentUser", expression = "java(userMapper.userToUserDto(getCurrentUser(friendship, currentUserId)))")
    FriendshipDto friendshipToFriendshipDto(Friendship friendship, String currentUserId, @Context UserMapper userMapper);

    List<FriendshipOutput> friendshipDtoListToFriendshipOutputList(List<FriendshipDto> friendshipDtos);

    FriendshipOutput friendshipDtoToFriendshipOutput(FriendshipDto friendshipDto);

    default User getOtherUser(Friendship friendship, String currentUserId) {
        return friendship.getUser1().getId().equals(currentUserId) ? friendship.getUser2() : friendship.getUser1();
    }

    default User getCurrentUser(Friendship friendship, String currentUserId) {
        return friendship.getUser1().getId().equals(currentUserId) ? friendship.getUser1() : friendship.getUser2();
    }
}
