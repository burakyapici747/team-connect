package com.teamconnect.model.sql;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "MEETING")
public class Meeting extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = true)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingParticipant> meetingParticipants;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "is_recurring", nullable = false)
    private boolean isRecurring;
}
