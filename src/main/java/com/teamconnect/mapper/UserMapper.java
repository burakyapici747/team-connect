package com.teamconnect.mapper;

import com.teamconnect.dto.UserDto;
import com.teamconnect.model.sql.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
}
