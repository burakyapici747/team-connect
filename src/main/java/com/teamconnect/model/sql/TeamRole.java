package com.teamconnect.model.sql;

import java.util.Set;

import com.teamconnect.common.enumarator.TeamPermission;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "team_roles")
public class TeamRole extends BaseModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "permission")
    private Set<TeamPermission> permissions;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault = false;
}
