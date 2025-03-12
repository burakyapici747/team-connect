package com.teamconnect.dto;

import com.teamconnect.api.output.user.AuthorOutput;
import com.teamconnect.model.nosql.Attachment;
import com.teamconnect.model.nosql.Mention;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebSocketMessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String channelId;
    private String content;
    private Long timestamp;
    private Instant editedTimestamp;
    private Boolean pinned;
    private Integer type;
    private Set<Attachment> attachments;
    private Set<Mention> mentions;
    private List<Map<String, Object>> reactions;
    private AuthorOutput author;

    public WebSocketMessageDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
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

    public AuthorOutput getAuthor() {
        return author;
    }

    public void setAuthor(AuthorOutput author) {
        this.author = author;
    }
}
