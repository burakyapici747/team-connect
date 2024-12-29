package com.teamconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.teamconnect.api.input.user.UserRegisterInput;
import com.teamconnect.api.input.user.UserUpdateInput;
import com.teamconnect.api.output.user.UserDetailsPrivateOutput;
import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.dto.UserDto;
import com.teamconnect.model.sql.User;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastSeenAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "teamMembers", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "meetings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User userRegisterInputToUser(UserRegisterInput input);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastSeenAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "teamMembers", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "meetings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "name", source = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastName", source = "lastName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUpdateUserInput(UserUpdateInput input, @MappingTarget User user);

    UserDetailsPublicOutput userDtoToUserDetailsPublicOutput(UserDto userDto);

    UserDetailsPrivateOutput userDtoToUserDetailsPrivateOutput(UserDto userDto);
}
