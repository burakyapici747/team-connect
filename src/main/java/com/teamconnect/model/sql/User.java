package com.teamconnect.model.sql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User extends BaseModel {
    @OneToOne(cascade = CascadeType.ALL, targetEntity = UserProfile.class, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<TeamMember> teamMembers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Meeting> meetings;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "last_seen_at", nullable = false)
    private Instant lastSeenAt;
}
