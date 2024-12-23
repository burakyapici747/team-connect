package com.teamconnect.mapper;

import com.teamconnect.api.output.*;
import com.teamconnect.dto.UserDto;
import com.teamconnect.model.sql.User;
import org.mapstruct.Mapper;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import com.teamconnect.api.input.*;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    RegisterOutput userDtoToRegisterOutput(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastSeenAt", ignore = true)
    @Mapping(target = "meetings", ignore = true)
    @Mapping(target = "teamMembers", ignore = true)
    User registerInputToUser(RegisterInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastSeenAt", ignore = true)
    @Mapping(target = "meetings", ignore = true)
    @Mapping(target = "teamMembers", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    void updateUserFromUpdateUserInput(@MappingTarget User user, UpdateUserInput input);

    GetUserOutput userDtoToGetUserOutput(UserDto userDto);
    UpdateUserOutput userDtoToUpdateUserOutput(UserDto userDto);
    List<SearchUserOutput> userDtoListToSearchUserOutputList(List<UserDto> userDtoList);
    SearchUserOutput userDtoToSearchUserOutput(UserDto userDto);
}
