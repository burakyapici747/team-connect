package com.teamconnect.model.sql;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends BaseModel{
    @Column(name = "bio", nullable = true)
    private String bio;
    @Column(name = "profile_image_url", nullable = true)
    private String profileImageUrl;
    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "date_of_birth", nullable = true)
    private Instant dateOfBirth;
    @Column(name = "language", nullable = true)
    private String language;
    @Column(name = "availability", nullable = false)
    @Enumerated(EnumType.STRING)
    private String availability;
}
