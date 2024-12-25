package com.teamconnect.api.controller;

import com.teamconnect.api.input.user.*;
import com.teamconnect.api.output.*;
import com.teamconnect.api.output.user.UserDetailsPrivateOutput;
import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.api.output.user.UserProfilePrivateOutput;
import com.teamconnect.api.output.user.UserProfilePublicOutput;
import com.teamconnect.common.enumarator.Availability;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.mapper.UserProfileMapper;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserDetailsPublicOutput>> getUserById(@PathVariable String id) {
        return ResponseWrapper.ok(
            UserMapper.INSTANCE.userDtoToUserDetailsPublicOutput(
                userService.getUserById(id)
            )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper<UserDetailsPrivateOutput>> getCurrentUser(
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseWrapper.ok(
            UserMapper.INSTANCE.userDtoToUserDetailsPrivateOutput(
                userService.getUserByEmail(userDetails.getUsername())
            )
        );
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<UserDetailsPrivateOutput>> updateUser(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody UserUpdateInput input
    ) {
        return ResponseWrapper.ok(
            UserMapper.INSTANCE.userDtoToUserDetailsPrivateOutput(
                userService.updateUserByEmail(userDetails.getUsername(), input)
            ),
            "User updated successfully"
        );
    }

    @PutMapping("/password")
    public ResponseEntity<ResponseWrapper<Void>> updateUserPassword(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody UserUpdatePasswordInput input
    ) {
        userService.updateUserPassword(userDetails.getUsername(), input);
        return ResponseWrapper.noContent();
    }

    @PutMapping("/me/availability")
    public ResponseEntity<ResponseWrapper<Availability>> updateAvailability(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody UserUpdateAvailabilityInput input
    ) {
        return ResponseWrapper.ok(
            userService.updateAvailabilityByUserEmail(userDetails.getUsername(), input),
            "Availability updated successfully"
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<UserDetailsPrivateOutput>> register(
        @Valid @RequestBody UserRegisterInput input
    ) {
        return ResponseWrapper.ok(
            UserMapper.INSTANCE.userDtoToUserDetailsPrivateOutput(userService.createUser(input))
        );
    }

    @DeleteMapping
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody UserDeleteInput input
    ) {
        userService.deleteUserByEmail(userDetails.getUsername(), input);
        return ResponseWrapper.noContent();
    }

    @PutMapping("/me/profile")
    public ResponseEntity<ResponseWrapper<UserProfilePrivateOutput>> updateProfile(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody UserUpdateProfileInput input
    ) {
        UserProfileDto profileDto = userService.updateUserProfileByUserEmail(userDetails.getUsername(), input);
        return ResponseWrapper.ok(
            UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePrivateOutput(profileDto),
            "Profile updated successfully"
        );
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ResponseWrapper<UserProfilePublicOutput>> getUserProfile(
        @PathVariable String userId
    ) {
        return ResponseWrapper.ok(
            UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePublicOutput(
                userService.getUserProfileByUserId(userId)
            )
        );
    }

    @GetMapping("/me/profile")
    public ResponseEntity<ResponseWrapper<UserProfilePrivateOutput>> getCurrentUserProfile(
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseWrapper.ok(
            UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePrivateOutput(
                userService.getUserProfileByUserEmail(userDetails.getUsername())
            )
        );
    }
}
