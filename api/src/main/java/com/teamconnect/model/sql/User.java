package com.teamconnect.model.sql;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User extends BaseModel {
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "discriminator", nullable = false)
    private String discriminator;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar_hash")
    private String avatar;

    @Column(name = "locale")
    private String locale;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
