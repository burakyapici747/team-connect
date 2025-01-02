package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.FileType;
import jakarta.persistence.*;
import java.time.Instant;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
@TypeAlias("file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Field
    private String userId;
    @Field
    private String name;
    @Field
    @Enumerated(EnumType.STRING)
    private FileType type;
    @Field
    private int sizeInKb;
    @Field
    private String url;
    @Field
    private Instant createdAt;
    @Field
    private Instant updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public int getSizeInKb() {
        return sizeInKb;
    }

    public void setSizeInKb(int sizeInKb) {
        this.sizeInKb = sizeInKb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
