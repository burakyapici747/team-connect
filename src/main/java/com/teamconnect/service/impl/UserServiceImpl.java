package com.teamconnect.service.impl;

import com.teamconnect.api.input.*;
import com.teamconnect.dto.UserDto;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.UserRepository;
import com.teamconnect.service.UserService;
import com.teamconnect.common.enumarator.Availability;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto register(RegisterInput input) {
        validateEmailNotExists(input.email());
        User user = userMapper.registerInputToUser(input);
        user.setPassword(passwordEncoder.encode(input.password()));
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUser(String userId) {
        return userMapper.userToUserDto(findUserById(userId));
    }

    @Override
    @Transactional
    public UserDto updateUser(UpdateUserInput input) {
        User user = findUserById(input.userId());
        userMapper.updateUserFromUpdateUserInput(user, input);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(SecureOperationInput input) {
        User user = findUserById(input.userId());
        validatePassword(input.password(), user.getPassword());
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void updatePassword(UpdatePasswordInput input) {
        User user = findUserById(input.userId());
        validatePassword(input.currentPassword(), user.getPassword());
        user.setPassword(passwordEncoder.encode(input.newPassword()));
        userRepository.save(user);
    }

    @Override
    public List<UserDto> searchUsers(String keyword, Availability availability, String language) {
        List<User> users = userRepository.searchUsers(keyword, availability != null ? availability.name() : null, language);
        return users.stream()
                   .map(userMapper::userToUserDto)
                   .toList();
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private void validateEmailNotExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }
    }
}
