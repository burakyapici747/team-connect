package com.teamconnect.model.sql;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "TEAM")
public class Team extends BaseModel{
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TeamMember> teamMembers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Set<TeamRole> teamRoles = new HashSet<>();

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Meeting> meetings = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    public Set<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Set<TeamRole> getTeamRoles() {
        return teamRoles;
    }

    public void setTeamRoles(Set<TeamRole> teamRoles) {
        this.teamRoles = teamRoles;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
