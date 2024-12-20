package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.MeetingRole;
import com.teamconnect.common.enumarator.MeetingStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "MEETING_PARTICIPANT")
public class MeetingParticipant extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id", nullable = false)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(name = "meeting_role", nullable = false)
    private MeetingRole meetingRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "meeting_status", nullable = false)
    private MeetingStatus meetingStatus;

    @Column(name = "joined_at", nullable = false)
    private Instant joinedAt;
}
