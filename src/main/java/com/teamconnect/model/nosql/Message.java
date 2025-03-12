package com.teamconnect.model.nosql;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.Instant;
import java.util.*;

@Document
@TypeAlias("MESSAGE")
public class Message extends BaseCouchbaseModel{
    private String channelId;
    private String authorId;
    private String content;
    private Long timestamp;
    private Instant editedTimestamp;
    private Boolean pinned;
    private Integer type;
    private Set<Attachment> attachments = new HashSet<>();
    private Set<Mention> mentions = new HashSet<>();
    private List<Map<String, Object>> reactions = new ArrayList<>();

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getEditedTimestamp() {
        return editedTimestamp;
    }

    public void setEditedTimestamp(Instant editedTimestamp) {
        this.editedTimestamp = editedTimestamp;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Set<Mention> getMentions() {
        return mentions;
    }

    public void setMentions(Set<Mention> mentions) {
        this.mentions = mentions;
    }

    public List<Map<String, Object>> getReactions() {
        return reactions;
    }

    public void setReactions(List<Map<String, Object>> reactions) {
        this.reactions = reactions;
    }
}
