package com.teamconnect.model.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;

import lombok.Getter;

@Getter
@Entity
@Table(name = "team_roles")
public class TeamRole extends BaseModel {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;
}
