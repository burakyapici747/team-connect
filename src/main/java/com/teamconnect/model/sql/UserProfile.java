package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.Availability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_PROFILES")
public class UserProfile extends BaseModel {
    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_image_file_id")
    private String profileImageFileId;

    @Column(name = "language")
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    private Availability availability;

    @Column(name = "status_description")
    private String statusDescription;
}
