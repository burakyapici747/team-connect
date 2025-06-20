package com.teamconnect.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.teamconnect.api.input.user.*;
import com.teamconnect.common.enumarator.FilePurposeType;
import com.teamconnect.dto.*;
import com.teamconnect.model.nosql.File;
import com.teamconnect.model.sql.UserRole;
import com.teamconnect.service.ChannelService;
import com.teamconnect.service.FileService;
import com.teamconnect.service.TeamService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamconnect.common.enumarator.Role;
import com.teamconnect.exception.UserAlreadyExistsException;
import com.teamconnect.exception.UserNotFoundException;
import com.teamconnect.mapper.UserMapper;
import com.teamconnect.mapper.UserProfileMapper;
import com.teamconnect.model.sql.User;
import com.teamconnect.model.sql.UserProfile;
import com.teamconnect.repository.postgresql.UserProfileRepository;
import com.teamconnect.repository.postgresql.UserRepository;
import com.teamconnect.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {
    private final TeamService teamService;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final ChannelService channelService;
    private final FileService fileService;

    public UserServiceImpl(
        UserRepository userRepository,
        UserProfileRepository userProfileRepository,
        PasswordEncoder passwordEncoder,
        @Lazy TeamService teamService,
        ChannelService channelService,
        FileService fileService
    ) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.teamService = teamService;
        this.channelService = channelService;
        this.fileService = fileService;
    }

    @Override
    public UserDto getUserById(String id) {
        return UserMapper.INSTANCE.userToUserDto(findUserById(id));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserMapper.INSTANCE.userToUserDto(findUserByEmail(email));
    }

    @Override
    public List<TeamDto> getUserTeamsByUserId(String userId) {
        return teamService.getTeamsByUserId(userId);
    }

    @Override
    public List<ChannelDto> getUserChannelsByUserId(String userId) {
        return channelService.getChannelsByUserId(userId);
    }

    @Override
    public UserDto createUser(UserRegisterInput input) {
        validateUserEmailIsUnique(input.email());
        User user = createNewUser(input);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUserByEmail(String email, UserUpdateInput input) {
        User user = findUserByEmail(email);

        validateUserEmailIsUniqueForUpdate(email, user.getId());
        UserMapper.INSTANCE.updateUserFromUpdateUserInput(input, user);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public void updateUserPassword(String email, UserUpdatePasswordInput input) {
        User user = findUserByEmail(email);
        user.setPassword(passwordEncoder.encode(input.password()));
        userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email, UserDeleteInput input) {
        User user = findUserByEmail(email);
        validatePasswordMatchesForDeletion(input.password(), user.getPassword());
        userRepository.delete(findUserByEmail(email));
    }

    @Override
    public UserProfileDto updateUserProfileByUserEmail(String email, UserUpdateProfileInput input) {
        UserProfile userProfile = findUserProfileByUserEmail(email);
        UserProfileMapper.INSTANCE.updateUserProfileFromUpdateProfileInput(input, userProfile);
        userProfileRepository.save(userProfile);
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(userProfile);
    }

    @Override
    public UserProfileDto getUserProfileByUserId(String id) {
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(findUserProfileByUserId(id));
    }

    @Override
    public UserProfileDto getUserProfileByUserEmail(String email) {
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(findUserProfileByUserEmail(email));
    }

    @Override
    public User getUserEntityByEmail(String email) {
        return findUserByEmail(email);
    }

    @Override
    public UserProfileDto uploadUserProfileAvatar(String currentUserId, MultipartFile file) throws IOException {
        UserProfile userProfile = findUserProfileByUserId(currentUserId);
        File avatar = fileService.storeFile(file, FilePurposeType.AVATAR, currentUserId);
        userProfile.setAvatarFileId(avatar.getId());
        userProfileRepository.save(userProfile);
        return UserProfileMapper.INSTANCE.userProfileToUserProfileDto(userProfile);
    }

    @Override
    public List<UserDto> getAllByIdIn(Set<String> userIds) {
        return UserMapper.INSTANCE.userListToUserDtoList(userRepository.findAllByIdIn(userIds));
    }

    @Override
    public User getUserEntityById(String id) {
        return findUserById(id);
    }

    @Override
    public PageableDto<UserDto> getAll(){
        Pageable page = PageRequest.of(0, 10);
        Page<User> usersPage = userRepository.findAll(page);

        List<UserDto> userDtoList = UserMapper.INSTANCE.userListToUserDtoList(usersPage.getContent());

        return new PageableDto<>(
            userDtoList,
            usersPage.hasNext(),
            usersPage.getTotalPages(),
            usersPage.hasPrevious()
        );
    }

    private User findUserById(String id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserProfile findUserProfileByUserEmail(String email) {
        return findUserByEmail(email).getUserProfile();
    }

    private UserProfile findUserProfileByUserId(String userId) {
        return findUserById(userId).getUserProfile();
    }

    private void validateUserEmailIsUnique(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    private void validateUserEmailIsUniqueForUpdate(String email, String userId) {
        if (userRepository.existsByEmailAndIdNot(email, userId)) {
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    private void validatePasswordMatchesForDeletion(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("Password does not match");
        }
    }

    private User createNewUser(UserRegisterInput input) {
        User user = UserMapper.INSTANCE.userRegisterInputToUser(input);
        UserRole userRole = new UserRole();
        userRole.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRoles(Set.of(userRole));
        UserProfile userProfile = new UserProfile();
        user.setUserProfile(userProfile);
        return user;
    }
}
