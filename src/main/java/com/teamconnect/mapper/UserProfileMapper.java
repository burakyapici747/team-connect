package com.teamconnect.mapper;

import com.teamconnect.api.input.user.UserUpdateProfileInput;
import com.teamconnect.api.output.user.UserProfilePrivateOutput;
import com.teamconnect.api.output.user.UserProfilePublicOutput;
import com.teamconnect.dto.UserProfileDto;
import com.teamconnect.model.sql.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfileDto userProfileToUserProfileDto(UserProfile userProfile);

    UserProfilePublicOutput userProfileDtoToUserProfilePublicOutput(UserProfileDto userProfileDto);

    UserProfilePrivateOutput userProfileDtoToUserProfilePrivateOutput(UserProfileDto userProfileDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bio", source = "bio", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "language", source = "language", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "availability", source = "availability", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "profileImageFileId", source = "profileImageFileId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "statusDescription", source = "statusDescription", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserProfileFromUpdateProfileInput(UserUpdateProfileInput input, @MappingTarget UserProfile userProfile);
}
