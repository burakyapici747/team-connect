package com.teamconnect.service;

import com.teamconnect.api.input.user.UserDeleteInput;
import com.teamconnect.api.input.user.UserRegisterInput;
import com.teamconnect.api.input.user.UserUpdateInput;
import com.teamconnect.api.input.user.UserUpdatePasswordInput;
import com.teamconnect.api.input.user.UserUpdateProfileInput;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.dto.TeamDto;
import com.teamconnect.dto.UserDto;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.model.sql.User;

import java.util.List;

public interface UserService {
    List<TeamDto> getUserTeamsByUserId(String userId);

    List<ChannelDto> getUserChannelsByUserId(String userId);

    UserDto createUser(UserRegisterInput input);

    UserDto getUserById(String id);

    UserDto getUserByEmail(String email);

    UserDto updateUserByEmail(String email, UserUpdateInput input);

    void updateUserPassword(String email, UserUpdatePasswordInput input);

    UserProfileDto getUserProfileByUserId(String id);

    UserProfileDto getUserProfileByUserEmail(String email);

    void deleteUserByEmail(String email, UserDeleteInput input);

    UserProfileDto updateUserProfileByUserEmail(String email, UserUpdateProfileInput input);

    User getUserEntityById(String id);

    User getUserEntityByEmail(String email);
}
