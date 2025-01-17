package com.teamconnect.api.controller;

import com.teamconnect.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
}
