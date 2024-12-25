package com.teamconnect.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.teamconnect.api.input.meeting.MeetingCreateInput;
import com.teamconnect.api.input.meeting.MeetingUpdateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.meeting.MeetingCreateOutput;
import com.teamconnect.api.output.meeting.MeetingDetailsPrivateOutput;
import com.teamconnect.api.output.meeting.MeetingDetailsPublicOutput;
import com.teamconnect.mapper.MeetingMapper;
import com.teamconnect.service.MeetingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;
    private final MeetingMapper meetingMapper;

    @PostMapping
    @PreAuthorize("hasPermission(#input.teamId, 'MANAGE_MEETINGS')")
    public ResponseEntity<ResponseWrapper<MeetingCreateOutput>> createMeeting(
            @Valid @RequestBody MeetingCreateInput input,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.created(
                meetingMapper.meetingDtoToMeetingCreateOutput(
                        meetingService.createMeeting(input, userDetails.getUsername())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<MeetingDetailsPublicOutput>> getMeetingById(
            @PathVariable String id) {
        return ResponseWrapper.ok(
                meetingMapper.meetingDtoToMeetingDetailsPublicOutput(
                        meetingService.getMeetingById(id)));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<ResponseWrapper<List<MeetingDetailsPublicOutput>>> getTeamMeetings(
            @PathVariable String teamId) {
        return ResponseWrapper.ok(
                meetingService.getTeamMeetings(teamId).stream()
                        .map(meetingMapper::meetingDtoToMeetingDetailsPublicOutput)
                        .toList());
    }

    @GetMapping("/my-meetings")
    public ResponseEntity<ResponseWrapper<List<MeetingDetailsPrivateOutput>>> getUserMeetings(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                meetingService.getUserMeetings(userDetails.getUsername()).stream()
                        .map(meetingMapper::meetingDtoToMeetingDetailsPrivateOutput)
                        .toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'UPDATE_MEETING')")
    public ResponseEntity<ResponseWrapper<MeetingDetailsPrivateOutput>> updateMeeting(
            @PathVariable String id,
            @Valid @RequestBody MeetingUpdateInput input,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                meetingMapper.meetingDtoToMeetingDetailsPrivateOutput(
                        meetingService.updateMeeting(id, input, userDetails.getUsername())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'DELETE_MEETING')")
    public ResponseEntity<ResponseWrapper<Void>> deleteMeeting(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetails userDetails) {
        meetingService.deleteMeeting(id, userDetails.getUsername());
        return ResponseWrapper.noContent();
    }

    @PostMapping("/{id}/cancel")
    @PreAuthorize("hasPermission(#id, 'MANAGE_MEETINGS')")
    public ResponseEntity<ResponseWrapper<MeetingDetailsPrivateOutput>> cancelMeeting(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                meetingMapper.meetingDtoToMeetingDetailsPrivateOutput(
                        meetingService.cancelMeeting(id, userDetails.getUsername())));
    }

    @PostMapping("/{id}/start")
    @PreAuthorize("hasPermission(#id, 'MANAGE_MEETINGS')")
    public ResponseEntity<ResponseWrapper<MeetingDetailsPrivateOutput>> startMeeting(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                meetingMapper.meetingDtoToMeetingDetailsPrivateOutput(
                        meetingService.startMeeting(id, userDetails.getUsername())));
    }

    @PostMapping("/{id}/complete")
    @PreAuthorize("hasPermission(#id, 'MANAGE_MEETINGS')")
    public ResponseEntity<ResponseWrapper<MeetingDetailsPrivateOutput>> completeMeeting(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseWrapper.ok(
                meetingMapper.meetingDtoToMeetingDetailsPrivateOutput(
                        meetingService.completeMeeting(id, userDetails.getUsername())));
    }
}
