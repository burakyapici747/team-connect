package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.ChannelType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TEAM_CHANNEL")
public class TeamChannel extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ChannelType type = ChannelType.TEXT_CHANNEL;

    @Column(name = "position", nullable = false)
    private Integer position = 0;
}
