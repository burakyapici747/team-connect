package com.teamconnect.service;

import com.teamconnect.dto.UserProfileDto;

public interface UserProfileService {
    UserProfileDto getUserProfile(String userId);
}
