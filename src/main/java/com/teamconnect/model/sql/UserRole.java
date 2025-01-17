package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends BaseModel {
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
