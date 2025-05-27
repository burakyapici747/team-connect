package com.teamconnect.service;

import com.teamconnect.api.input.user.UserDeleteInput;
import com.teamconnect.api.input.user.UserRegisterInput;
import com.teamconnect.api.input.user.UserUpdateInput;
import com.teamconnect.api.input.user.UserUpdatePasswordInput;
import com.teamconnect.api.input.user.UserUpdateProfileInput;
import com.teamconnect.dto.*;
import com.teamconnect.model.sql.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    UserProfileDto uploadUserProfileAvatar(String currentUserId, MultipartFile file) throws IOException;

    List<UserDto> getAllByIdIn(Set<String> userIds);

    PageableDto<UserDto> getAll();
}
