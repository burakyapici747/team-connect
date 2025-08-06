package com.teamconnect.model.sql;

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

    @Column(name = "location")
    private String location;
}
