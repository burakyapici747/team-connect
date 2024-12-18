package com.teamconnect.service;

import com.teamconnect.dto.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email);
}
