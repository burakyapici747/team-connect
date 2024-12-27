package com.teamconnect.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamconnect.api.input.user.UserDeleteInput;
import com.teamconnect.api.input.user.UserRegisterInput;
import com.teamconnect.api.input.user.UserUpdateAvailabilityInput;
import com.teamconnect.api.input.user.UserUpdateInput;
import com.teamconnect.api.input.user.UserUpdatePasswordInput;
import com.teamconnect.api.input.user.UserUpdateProfileInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.user.UserDetailsPrivateOutput;
import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.api.output.user.UserProfilePrivateOutput;
import com.teamconnect.api.output.user.UserProfilePublicOutput;
import com.teamconnect.common.enumarator.Availability;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.mapper.UserProfileMapper;
import com.teamconnect.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserDetailsPublicOutput>> getUserById(@PathVariable String id) {
        return ResponseWrapper.ok(UserMapper.INSTANCE.userDtoToUserDetailsPublicOutput(userService.getUserById(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper<UserDetailsPrivateOutput>> getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                UserMapper.INSTANCE.userDtoToUserDetailsPrivateOutput(
                        userService.getUserByEmail(userDetails.getUsername())));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<UserDetailsPrivateOutput>> updateUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserUpdateInput input) {
        return ResponseWrapper.ok(
                UserMapper.INSTANCE.userDtoToUserDetailsPrivateOutput(
                        userService.updateUserByEmail(userDetails.getUsername(), input)),
                "User updated successfully");
    }

    @PutMapping("/password")
    public ResponseEntity<ResponseWrapper<Void>> updateUserPassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserUpdatePasswordInput input) {
        userService.updateUserPassword(userDetails.getUsername(), input);
        return ResponseWrapper.noContent();
    }

    @PutMapping("/me/availability")
    public ResponseEntity<ResponseWrapper<Availability>> updateAvailability(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserUpdateAvailabilityInput input) {
        return ResponseWrapper.ok(
                userService.updateAvailabilityByUserEmail(userDetails.getUsername(), input),
                "Availability updated successfully");
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<UserDetailsPrivateOutput>> register(
            @Valid @RequestBody UserRegisterInput input) {
        return ResponseWrapper.ok(
                UserMapper.INSTANCE.userDtoToUserDetailsPrivateOutput(userService.createUser(input)));
    }

    @DeleteMapping
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserDeleteInput input) {
        userService.deleteUserByEmail(userDetails.getUsername(), input);
        return ResponseWrapper.noContent();
    }

    @PutMapping("/me/profile")
    public ResponseEntity<ResponseWrapper<UserProfilePrivateOutput>> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserUpdateProfileInput input) {
        UserProfileDto profileDto = userService.updateUserProfileByUserEmail(userDetails.getUsername(), input);
        return ResponseWrapper.ok(
                UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePrivateOutput(profileDto),
                "Profile updated successfully");
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ResponseWrapper<UserProfilePublicOutput>> getUserProfile(
            @PathVariable String userId) {
        return ResponseWrapper.ok(
                UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePublicOutput(
                        userService.getUserProfileByUserId(userId)));
    }

    @GetMapping("/me/profile")
    public ResponseEntity<ResponseWrapper<UserProfilePrivateOutput>> getCurrentUserProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePrivateOutput(
                        userService.getUserProfileByUserEmail(userDetails.getUsername())));
    }
}
