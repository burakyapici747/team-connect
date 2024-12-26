package com.teamconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.teamconnect.api.input.meeting.MeetingCreateInput;
import com.teamconnect.api.input.meeting.MeetingUpdateInput;
import com.teamconnect.api.output.meeting.MeetingCreateOutput;
import com.teamconnect.api.output.meeting.MeetingDetailsPrivateOutput;
import com.teamconnect.api.output.meeting.MeetingDetailsPublicOutput;
import com.teamconnect.api.output.meeting.MeetingParticipantDetailsOutput;
import com.teamconnect.dto.MeetingDto;
import com.teamconnect.model.sql.Meeting;
import com.teamconnect.model.sql.MeetingParticipant;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface MeetingMapper {
    MeetingMapper INSTANCE = Mappers.getMapper(MeetingMapper.class);

    Meeting meetingCreateInputToMeeting(MeetingCreateInput input);

    void updateMeetingFromUpdateInput(MeetingUpdateInput input, @MappingTarget Meeting meeting);

    MeetingDto meetingToMeetingDto(Meeting meeting);

    @Mapping(target = "participantCount", expression = "java(meetingDto.participants().size())")
    MeetingDetailsPublicOutput meetingDtoToMeetingDetailsPublicOutput(MeetingDto meetingDto);

    MeetingDetailsPrivateOutput meetingDtoToMeetingDetailsPrivateOutput(MeetingDto meetingDto);

    MeetingCreateOutput meetingDtoToMeetingCreateOutput(MeetingDto meetingDto);

    @Mapping(target = "joinedAt", source = "createdAt")
    MeetingParticipantDetailsOutput participantToParticipantDetailsOutput(MeetingParticipant participant);
}
