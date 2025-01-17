package com.teamconnect.model.sql;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEAM")
public class Team extends BaseModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ElementCollection
    @CollectionTable(name = "team_channels", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "channel_id")
    private Set<String> channelList = new HashSet<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TeamMember> members = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<String> getChannelList() {
        return channelList;
    }

    public void setChannelList(Set<String> channelList) {
        this.channelList = channelList;
    }

    public Set<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(Set<TeamMember> members) {
        this.members = members;
    }
}
