package com.teamconnect.model.sql;

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
import java.util.Set;

@Entity
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TeamMemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(TeamMemberType memberType) {
        this.memberType = memberType;
    }

    public Set<TeamRole> getTeamRoles() {
        return teamRoles;
    }

    public void setTeamRoles(Set<TeamRole> teamRoles) {
        this.teamRoles = teamRoles;
    }
}
