package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.FilePurposeType;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;

@Document
@TypeAlias("FILE")
public class File {
    private String id;
    private String storedFileName;
    private String originalName;
    private String contentType;
    private long size;
    private Instant uploadDate;
    @Field("filePurpose")
    private FilePurposeType filePurpose;
    private String ownerId;
    private boolean isDeleted = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Instant getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Instant uploadDate) {
        this.uploadDate = uploadDate;
    }

    public FilePurposeType getFilePurpose() {
        return filePurpose;
    }

    public void setFilePurpose(FilePurposeType filePurpose) {
        this.filePurpose = filePurpose;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
