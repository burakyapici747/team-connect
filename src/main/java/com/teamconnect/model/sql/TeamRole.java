package com.teamconnect.model.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEAM_ROLE")
public class TeamRole extends BaseModel {
    private String name;
    private String value;
}
