package com.teamconnect.model.sql;

import java.util.Set;

import com.teamconnect.common.enumarator.TeamMemberType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEAM_MEMBER")
public class TeamMember extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private TeamMemberType memberType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "team_member_roles", joinColumns = @JoinColumn(name = "team_member_id"), inverseJoinColumns = @JoinColumn(name = "team_role_id"))
    private Set<TeamRole> teamRoles;
}
