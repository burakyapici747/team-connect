package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.Availability;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends BaseModel{
    @Column(name = "bio", nullable = true)
    private String bio;

    @Column(name = "profile_image_file_id", nullable = true)
    private String profileImageFileId;

    @Column(name = "date_of_birth", nullable = true)
    private Instant dateOfBirth;

    @Column(name = "language", nullable = true)
    private String language;

    @Column(name = "availability", nullable = false)
    @Enumerated(EnumType.STRING)
    private Availability availability;

    @Column(name = "status_description", nullable = true)
    private String statusDescription;
}
