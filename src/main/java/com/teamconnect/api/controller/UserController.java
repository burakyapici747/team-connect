package com.teamconnect.api.controller;

import com.teamconnect.api.input.team.TeamPublicOutput;
import com.teamconnect.api.output.channel.ChannelPublicOutput;
import com.teamconnect.mapper.ChannelMapper;
import com.teamconnect.mapper.TeamMapper;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.teamconnect.api.input.user.UserDeleteInput;
import com.teamconnect.api.input.user.UserRegisterInput;
import com.teamconnect.api.input.user.UserUpdateInput;
import com.teamconnect.api.input.user.UserUpdatePasswordInput;
import com.teamconnect.api.input.user.UserUpdateProfileInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.user.UserDetailsPrivateOutput;
import com.teamconnect.api.output.user.UserDetailsPublicOutput;
import com.teamconnect.api.output.user.UserProfilePrivateOutput;
import com.teamconnect.api.output.user.UserProfilePublicOutput;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.mapper.UserProfileMapper;
import com.teamconnect.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {
    private final UserService userService;
    private final UserProfileService userProfileService;

    public UserController(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/me/teams")
    public List<TeamPublicOutput> getCurrentUserAllTeams(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return TeamMapper.INSTANCE.teamDtoListToTeamPublicOutputList(
            userService.getUserTeamsByUserId(userDetails.getId())
        );
    }

    @GetMapping("/me/channels")
    public List<ChannelPublicOutput> getCurrentUserAllChannels(
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ChannelMapper.INSTANCE.channelDtoListToChannelPublicOutputlist(
            userService.getUserChannelsByUserId(userDetails.getId())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UserDetailsPublicOutput>> getUserById(@PathVariable String id) {
        return ResponseWrapper.ok(UserMapper.INSTANCE.userDtoToUserDetailsPublicOutput(userService.getUserById(id)));
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

    /*@PutMapping("/@me/availability")
    public ResponseEntity<ResponseWrapper<UserStatus>> updateAvailability(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody UserUpdateAvailabilityInput input
    ) {
        return ResponseWrapper.ok(
            userService.updateAvailabilityByUserEmail(userDetails.getUsername(), input),
            "Availability updated successfully"
        );
    }*/

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

    @PutMapping("/@me/profile")
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
    public ResponseEntity<ResponseWrapper<UserProfilePublicOutput>> getUserProfile(@PathVariable String userId) {
        return ResponseWrapper.ok(
            UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePublicOutput(
                userService.getUserProfileByUserId(userId))
        );
    }

    @GetMapping("/me/profile")
    public ResponseEntity<ResponseWrapper<UserProfilePrivateOutput>> getCurrentUserProfile(
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseWrapper.ok(
            UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePrivateOutput(
                userService.getUserProfileByUserEmail(userDetails.getUsername()))
        );
    }

    @PostMapping("/me/profile/avatar")
    public ResponseEntity<ResponseWrapper<UserProfilePrivateOutput>> uploadProfileAvatar(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestParam("avatar")MultipartFile multipartFile
    ) throws IOException {
        UserProfileDto profileDto = userService.uploadUserProfileAvatar(userDetails.getId(), multipartFile);
        return ResponseWrapper.ok(
            UserProfileMapper.INSTANCE.userProfileDtoToUserProfilePrivateOutput(profileDto),
            "Profile avatar uploaded successfully"
        );
    }
}
