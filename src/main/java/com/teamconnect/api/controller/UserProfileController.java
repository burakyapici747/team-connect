package com.teamconnect.api.controller;

import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.user.UserProfilePublicOutput;
import com.teamconnect.mapper.UserProfileMapper;
import com.teamconnect.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserProfilePublicOutput>> getUserProfile(@PathVariable String id) {
        return ResponseWrapper.ok(
            UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePublicOutput(
                userProfileService.getUserProfile(id)
            )
        );
    }
}
