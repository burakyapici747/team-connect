package com.teamconnect.model.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROLES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role extends BaseModel{
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "color")
    private Integer color;

    @Column(name = "hoist")
    private Boolean hoist;

    @Column(name = "position")
    private Integer position;

    @Column(name = "permissions")
    private Long permissions;

    @Column(name = "managed")
    private Boolean managed;

    @Column(name = "mentionable")
    private Boolean mentionable;

    @Column(name = "tags", columnDefinition = "TEXT")
    private String tagsJson;

    //TODO: User ve Guild ilişkileri ele alınacak.
}

