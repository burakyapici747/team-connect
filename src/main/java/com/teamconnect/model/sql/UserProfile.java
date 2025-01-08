package com.teamconnect.model.sql;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "user_profiles")
public class UserProfile extends BaseModel {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "title")
    private String title;

    @Column(name = "phone")
    private String phone;

    @Column(name = "location")
    private String location;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "language", length = 10)
    private String language = "en";

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "company")
    private String company;

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    @Column(name = "website")
    private String website;

    @Column(name = "theme_preference")
    private String themePreference = "light";

    @Type(value = JsonBinaryType.class)
    @Column(name = "notification_preferences", columnDefinition = "jsonb")
    private Map<String, Map<String, Boolean>> notificationPreferences = new HashMap<>();
}
