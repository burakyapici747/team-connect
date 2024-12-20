package com.teamconnect.model.sql;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USER")
public class User extends BaseModel{
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> teamMembers;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = UserProfile.class, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "status_message", nullable = true)
    private String statusMessage;

    @Column(name = "language", nullable = true)
    private String language;
}
