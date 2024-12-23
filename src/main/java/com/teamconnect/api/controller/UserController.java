package com.teamconnect.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.teamconnect.api.input.*;
import com.teamconnect.api.output.*;
import com.teamconnect.dto.UserDto;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.service.UserService;
import com.teamconnect.service.UserProfileService;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.service.SecurityService;
import lombok.RequiredArgsConstructor;
import com.teamconnect.common.enumarator.Availability;
import java.util.List;
import com.teamconnect.mapper.UserProfileMapper;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final SecurityService securityService;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<RegisterOutput>> register(@RequestBody RegisterInput input) {
        UserDto userDto = userService.register(input);
        RegisterOutput output = userMapper.userDtoToRegisterOutput(userDto);
        return ResponseWrapper.created(output, "User registered successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseWrapper<GetUserOutput>> getUser(
        @PathVariable String userId
    ) {
        securityService.validateUserAccess(userId);
        UserDto userDto = userService.getUser(userId);
        GetUserOutput output = userMapper.userDtoToGetUserOutput(userDto);
        return ResponseWrapper.ok(output);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseWrapper<UpdateUserOutput>> updateUser(
        @PathVariable String userId,
        @RequestBody UpdateUserInput input
    ) {
        securityService.validateUserAccess(userId);
        UserDto userDto = userService.updateUser(input);
        UpdateUserOutput output = userMapper.userDtoToUpdateUserOutput(userDto);
        return ResponseWrapper.ok(output, "User updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(
        @PathVariable String userId,
        @RequestBody SecureOperationInput input
    ) {
        securityService.validateSecureOperation(userId, input.password());
        userService.deleteUser(input);
        return ResponseWrapper.noContent();
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<ResponseWrapper<Void>> updatePassword(
        @PathVariable String userId,
        @RequestBody UpdatePasswordInput input
    ) {
        securityService.validateSecureOperation(userId, input.currentPassword());
        userService.updatePassword(input);
        return ResponseWrapper.ok(null, "Password updated successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseWrapper<List<SearchUserOutput>>> searchUsers(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) Availability availability,
        @RequestParam(required = false) String language
    ) {
        List<UserDto> users = userService.searchUsers(keyword, availability, language);
        List<SearchUserOutput> outputs = userMapper.userDtoListToSearchUserOutputList(users);
        return ResponseWrapper.ok(outputs);
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ResponseWrapper<GetProfileOutput>> getProfile(
        @PathVariable String userId
    ) {
        securityService.validateUserAccess(userId);
        UserProfileDto profileDto = userProfileService.getProfile(userId);
        GetProfileOutput output = userProfileMapper.userProfileDtoToGetProfileOutput(profileDto);
        return ResponseWrapper.ok(output);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<ResponseWrapper<UpdateProfileOutput>> updateProfile(
        @PathVariable String userId,
        @RequestBody UpdateProfileInput input
    ) {
        securityService.validateUserAccess(userId);
        UserProfileDto profileDto = userProfileService.updateProfile(input);
        UpdateProfileOutput output = userProfileMapper.userProfileDtoToUpdateProfileOutput(profileDto);
        return ResponseWrapper.ok(output, "Profile updated successfully");
    }

    @PutMapping("/{userId}/profile/status")
    public ResponseEntity<ResponseWrapper<UpdateStatusOutput>> updateStatus(
        @PathVariable String userId,
        @RequestBody UpdateStatusInput input
    ) {
        securityService.validateUserAccess(userId);
        UserProfileDto profileDto = userProfileService.updateStatus(input);
        UpdateStatusOutput output = userProfileMapper.userProfileDtoToUpdateStatusOutput(profileDto);
        return ResponseWrapper.ok(output, "Status updated successfully");
    }

    @PutMapping("/{userId}/profile/availability")
    public ResponseEntity<ResponseWrapper<UpdateAvailabilityOutput>> updateAvailability(
        @PathVariable String userId,
        @RequestBody UpdateAvailabilityInput input
    ) {
        securityService.validateUserAccess(userId);
        UserProfileDto profileDto = userProfileService.updateAvailability(input);
        UpdateAvailabilityOutput output = userProfileMapper.userProfileDtoToUpdateAvailabilityOutput(profileDto);
        return ResponseWrapper.ok(output, "Availability updated successfully");
    }
}