package com.teamconnect.service.impl;

import com.teamconnect.api.input.*;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.mapper.UserProfileMapper;
import com.teamconnect.model.sql.User;
import com.teamconnect.model.sql.UserProfile;
import com.teamconnect.repository.UserRepository;
import com.teamconnect.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileDto getProfile(String userId) {
        User user = findUserById(userId);
        validateProfileExists(user);
        return userProfileMapper.userProfileToDto(user.getUserProfile());
    }

    @Override
    @Transactional
    public UserProfileDto updateProfile(UpdateProfileInput input) {
        User user = findUserById(input.userId());
        validateProfileExists(user);
        UserProfile profile = user.getUserProfile();
        userProfileMapper.updateUserProfileFromUpdateProfileInput(profile, input);
        userRepository.save(user);
        return userProfileMapper.userProfileToDto(profile);
    }

    @Override
    @Transactional
    public UserProfileDto updateStatus(UpdateStatusInput input) {
        User user = findUserById(input.userId());
        validateProfileExists(user);
        UserProfile profile = user.getUserProfile();
        profile.setStatusDescription(input.statusDescription());
        userRepository.save(user);
        return userProfileMapper.userProfileToDto(profile);
    }

    @Override
    @Transactional
    public UserProfileDto updateAvailability(UpdateAvailabilityInput input) {
        User user = findUserById(input.userId());
        validateProfileExists(user);
        UserProfile profile = user.getUserProfile();
        profile.setAvailability(input.availability());
        userRepository.save(user);
        return userProfileMapper.userProfileToDto(profile);
    }

    private User findUserById(String userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private void validateProfileExists(User user) {
        if (user.getUserProfile() == null) {
            throw new EntityNotFoundException("Profile not found for user");
        }
    }
}
