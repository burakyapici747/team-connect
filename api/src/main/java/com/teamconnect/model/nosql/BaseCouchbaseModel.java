package com.teamconnect.model.nosql;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseCouchbaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Field(name = "created_at")
    private Instant createdAt;

    @Field(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
