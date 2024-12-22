package com.teamconnect.model.sql;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "TEAM_MEMBER")
public class TeamMember extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @ManyToMany
    @JoinTable(
            name = "TEAM_MEMBER_ROLE",
            joinColumns = @JoinColumn(name = "team_member_id"),
            inverseJoinColumns = @JoinColumn(name = "team_role_id")
    )
    private Set<TeamRole> teamRoles;

    @Column(name = "joined_at", nullable = false)
    private Instant joinedAt;
}
