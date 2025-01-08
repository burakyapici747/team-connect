package com.teamconnect.service.impl;

import java.util.Set;

import com.teamconnect.common.enumarator.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamconnect.api.input.user.UserDeleteInput;
import com.teamconnect.api.input.user.UserRegisterInput;
import com.teamconnect.api.input.user.UserUpdateAvailabilityInput;
import com.teamconnect.api.input.user.UserUpdateInput;
import com.teamconnect.api.input.user.UserUpdatePasswordInput;
import com.teamconnect.api.input.user.UserUpdateProfileInput;
import com.teamconnect.common.enumarator.Role;
import com.teamconnect.dto.UserDto;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.exception.UserAlreadyExistsException;
import com.teamconnect.exception.UserNotFoundException;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.mapper.UserProfileMapper;
import com.teamconnect.model.sql.User;
import com.teamconnect.model.sql.UserProfile;
import com.teamconnect.repository.UserProfileRepository;
import com.teamconnect.repository.UserRepository;
import com.teamconnect.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserById(String id) {
        return UserMapper.INSTANCE.userToUserDto(findUserById(id));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserMapper.INSTANCE.userToUserDto(findUserByEmail(email));
    }

    @Override
    public UserDto createUser(UserRegisterInput input) {
        validateUserEmailIsUnique(input.email());
        User user = UserMapper.INSTANCE.userRegisterInputToUser(input);
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRoles(Set.of(Role.ROLE_USER));
        UserProfile userProfile = new UserProfile();
        userProfile.setAvailability(UserStatus.ONLINE);
        user.setUserProfile(userProfile);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUserByEmail(String email, UserUpdateInput input) {
        User user = findUserByEmail(email);

        validateUserEmailIsUniqueForUpdate(email, user.getId());
        UserMapper.INSTANCE.updateUserFromUpdateUserInput(input, user);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public void updateUserPassword(String email, UserUpdatePasswordInput input) {
        User user = findUserByEmail(email);
        user.setPassword(passwordEncoder.encode(input.password()));
        userRepository.save(user);
    }

    @Override
    public UserStatus updateAvailabilityByUserEmail(String email, UserUpdateAvailabilityInput input) {
        UserProfile userProfile = findUserProfileByUserEmail(email);
        userProfile.setAvailability(input.userStatus());
        userProfileRepository.save(userProfile);
        return userProfile.getAvailability();
    }

    @Override
    public void deleteUserByEmail(String email, UserDeleteInput input) {
        User user = findUserByEmail(email);
        validatePasswordMatchesForDeletion(input.password(), user.getPassword());
        userRepository.delete(findUserByEmail(email));
    }

    @Override
    public UserProfileDto updateUserProfileByUserEmail(String email, UserUpdateProfileInput input) {
        UserProfile userProfile = findUserProfileByUserEmail(email);
        UserProfileMapper.INSTANCE.updateUserProfileFromUpdateProfileInput(input, userProfile);
        userProfileRepository.save(userProfile);
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(userProfile);
    }

    @Override
    public UserProfileDto getUserProfileByUserId(String id) {
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(findUserProfileByUserId(id));
    }

    @Override
    public UserProfileDto getUserProfileByUserEmail(String email) {
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(findUserProfileByUserEmail(email));
    }

    @Override
    public User getUserEntityByEmail(String email) {
        return findUserByEmail(email);
    }

    private User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserProfile findUserProfileByUserEmail(String email) {
        return findUserByEmail(email).getUserProfile();
    }

    private UserProfile findUserProfileByUserId(String userId) {
        return findUserById(userId).getUserProfile();
    }

    private void validateUserEmailIsUnique(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    private void validateUserEmailIsUniqueForUpdate(String email, String userId) {
        if (userRepository.existsByEmailAndIdNot(email, userId)) {
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    private void validatePasswordMatchesForDeletion(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("Password does not match");
        }
    }

    @Override
    public User getUserEntityById(String id) {
        return findUserById(id);
    }
}
