package com.teamconnect.model.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role extends BaseModel {
    private String name;
    private String description;
}
