package com.teamconnect.model.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEAM_PERMISSION")
public class TeamMemberPermission extends BaseModel{
    private String name;
    private String description;
}