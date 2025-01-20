package com.teamconnect.service.impl;

import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.mapper.UserProfileMapper;
import com.teamconnect.model.sql.User;
import com.teamconnect.repository.postgresql.UserProfileRepository;
import com.teamconnect.service.UserProfileService;
import com.teamconnect.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserService userService){
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
    }

    @Override
    public UserProfileDto getUserProfile(String userId) {
        User user = userService.getUserEntityById(userId);
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(user.getUserProfile());
    }
}
