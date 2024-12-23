package com.teamconnect.service;

import com.teamconnect.api.input.*;
import com.teamconnect.dto.UserProfileDto;

public interface UserProfileService {
    UserProfileDto getProfile(String userId);
    UserProfileDto updateProfile(UpdateProfileInput input);
    UserProfileDto updateStatus(UpdateStatusInput input);
    UserProfileDto updateAvailability(UpdateAvailabilityInput input);
}
