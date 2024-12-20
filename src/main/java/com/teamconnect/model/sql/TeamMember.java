package com.teamconnect.model.sql;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "TEAM_MEMBER")
public class TeamMember extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "joined_at", nullable = false)
    private Instant joinedAt;

    @ManyToMany
    @JoinTable(
            name = "TEAM_MEMBER_ROLE",
            joinColumns = @JoinColumn(name = "team_member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "TEAM_MEMBER_PERMISSION",
            joinColumns = @JoinColumn(name = "team_member_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<TeamMemberPermission> teamMemberPermissions;
}
