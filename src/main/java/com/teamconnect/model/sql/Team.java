package com.teamconnect.model.sql;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TEAM")
public class Team extends BaseModel{
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TeamMember> teamMembers;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Set<TeamRole> teamRoles;

    @OneToMany(mappedBy = "team", targetEntity = Meeting.class, fetch = FetchType.LAZY)
    private List<Meeting> meetings;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;
}
