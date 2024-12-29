package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.Availability;
import jakarta.persistence.*;

@Entity
@Table(name = "USER_PROFILE")
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfileImageFileId() {
        return profileImageFileId;
    }

    public void setProfileImageFileId(String profileImageFileId) {
        this.profileImageFileId = profileImageFileId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    
}
