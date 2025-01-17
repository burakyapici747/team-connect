package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.UserStatus;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(Instant lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Team> getOwnedTeams() {
        return ownedTeams;
    }

    public void setOwnedTeams(Set<Team> ownedTeams) {
        this.ownedTeams = ownedTeams;
    }

    public Set<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Friendship> getSentFriendships() {
        return sentFriendships;
    }

    public void setSentFriendships(Set<Friendship> sentFriendships) {
        this.sentFriendships = sentFriendships;
    }

    public Set<Friendship> getReceivedFriendships() {
        return receivedFriendships;
    }

    public void setReceivedFriendships(Set<Friendship> receivedFriendships) {
        this.receivedFriendships = receivedFriendships;
    }
}
