package com.teamconnect.model;

import com.teamconnect.common.enumarator.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends BaseModel{
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Role role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }
}
