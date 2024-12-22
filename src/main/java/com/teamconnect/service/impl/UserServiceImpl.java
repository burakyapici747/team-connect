package com.teamconnect.service.impl;

import com.teamconnect.dto.UserDto;
import com.teamconnect.exception.UserNotFoundException;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.UserRepository;
import com.teamconnect.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = findUserByEmail(email);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
