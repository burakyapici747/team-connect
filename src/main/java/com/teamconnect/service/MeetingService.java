package com.teamconnect.service;

import java.util.List;

import com.teamconnect.api.input.meeting.MeetingCreateInput;
import com.teamconnect.api.input.meeting.MeetingUpdateInput;
import com.teamconnect.dto.MeetingDto;

public interface MeetingService {
    MeetingDto createMeeting(MeetingCreateInput input, String email);

    MeetingDto getMeetingById(String id);

    List<MeetingDto> getTeamMeetings(String teamId);

    List<MeetingDto> getUserMeetings(String email);

    MeetingDto updateMeeting(String id, MeetingUpdateInput input, String email);

    void deleteMeeting(String id, String email);

    MeetingDto cancelMeeting(String id, String email);

    MeetingDto startMeeting(String id, String email);

    MeetingDto completeMeeting(String id, String email);
}