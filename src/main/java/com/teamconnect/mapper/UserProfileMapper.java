package com.teamconnect.mapper;

import com.teamconnect.api.input.*;
import com.teamconnect.api.output.*;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.model.sql.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfileDto userProfileToDto(UserProfile profile);
    GetProfileOutput userProfileDtoToGetProfileOutput(UserProfileDto profileDto);
    UpdateProfileOutput userProfileDtoToUpdateProfileOutput(UserProfileDto profileDto);
    UpdateStatusOutput userProfileDtoToUpdateStatusOutput(UserProfileDto profileDto);
    UpdateAvailabilityOutput userProfileDtoToUpdateAvailabilityOutput(UserProfileDto profileDto);

    void updateUserProfileFromUpdateProfileInput(@MappingTarget UserProfile profile, UpdateProfileInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bio", source = "bio")
    @Mapping(target = "profileImageFileId", source = "profileImageFileId")
    @Mapping(target = "language", source = "language")
    @Mapping(target = "availability", source = "availability")
    @Mapping(target = "statusDescription", source = "statusDescription")
    UserProfile userProfileDtoToUserProfile(UserProfileDto dto);
}
