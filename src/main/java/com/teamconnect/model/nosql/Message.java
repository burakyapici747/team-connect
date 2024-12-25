package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.MessageTargetType;
import jakarta.persistence.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Document
@TypeAlias("message")
public class Message {
    @Id
    private String id;
    @Field
    private String receiverId;
    @Field
    private String senderId;
    @Field
    private String parentMessageId;
    @Field
    @Enumerated(EnumType.STRING)
    private MessageTargetType targetType;
    @Field
    private String content;
    @Field
    private List<Reaction> reactions;
    @Field
    private boolean isDeleted;
    @Field
    private boolean isPinned;
    @Field
    private Instant createdAt;
    @Field
    private Instant updatedAt;

    public static class Reaction {
        private String userId;
        private String reactionType;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getReactionType() {
            return reactionType;
        }

        public void setReactionType(String reactionType) {
            this.reactionType = reactionType;
        }
    }

    public static class Attachment{
        private String fileId;

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(String parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public MessageTargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(MessageTargetType targetType) {
        this.targetType = targetType;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
