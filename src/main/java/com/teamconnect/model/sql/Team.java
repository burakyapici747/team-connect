package com.teamconnect.model.sql;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TEAM")
public class Team extends BaseModel{
    @OneToMany(
            mappedBy = "team",
            targetEntity = Meeting.class,
            fetch = FetchType.LAZY
    )
    private List<Meeting> meetings;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> teamMembers;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;
}
