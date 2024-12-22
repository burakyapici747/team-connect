package com.teamconnect.service;

import com.teamconnect.api.input.*;
import com.teamconnect.dto.UserDto;
import com.teamconnect.common.enumarator.Availability;
import com.teamconnect.model.sql.User;
import java.util.List;

public interface UserService {
    UserDto register(RegisterInput input);
    UserDto getUser(String userId);
    UserDto updateUser(UpdateUserInput input);
    void deleteUser(SecureOperationInput input);
    void updatePassword(UpdatePasswordInput input);
    List<UserDto> searchUsers(String keyword, Availability availability, String language);
    User findUserById(String userId);
    User findUserByEmail(String email);
}
