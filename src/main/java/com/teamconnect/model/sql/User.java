package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User extends BaseModel {
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status = UserStatus.OFFLINE;

    @Column(name = "last_seen_at")
    private Instant lastSeenAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Team> ownedTeams = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TeamMember> teamMembers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user1", fetch = FetchType.LAZY)
    private Set<Friendship> sentFriendships = new HashSet<>();

    @OneToMany(mappedBy = "user2", fetch = FetchType.LAZY)
    private Set<Friendship> receivedFriendships = new HashSet<>();
}
