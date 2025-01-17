package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.ChannelType;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Document
@TypeAlias("CHANNEL")
public class Channel extends BaseCouchbaseModel {
    private ChannelType type = ChannelType.DIRECT_CHANNEL;
    private String teamId;
    private Integer position;
    private String name;
    private String topic;
    private Boolean nsfw;
    private String lastMessageId;
    private Integer userLimit;
    private Integer rateLimitPerUser;
    private Set<String> recipients;
    private String icon;
    private Map<String, List<String>> permissionOverwrites;
    private String ownerId;

    public ChannelType getType() {
        return type;
    }

    public void setType(ChannelType type) {
        this.type = type;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(String lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public Integer getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
    }

    public Integer getRateLimitPerUser() {
        return rateLimitPerUser;
    }

    public void setRateLimitPerUser(Integer rateLimitPerUser) {
        this.rateLimitPerUser = rateLimitPerUser;
    }

    public Set<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<String> recipients) {
        this.recipients = recipients;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, List<String>> getPermissionOverwrites() {
        return permissionOverwrites;
    }

    public void setPermissionOverwrites(Map<String, List<String>> permissionOverwrites) {
        this.permissionOverwrites = permissionOverwrites;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
