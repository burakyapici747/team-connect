package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.FilePurposeType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@Document
@TypeAlias("FILE")
public class File {
    private String id;
    private String storedFileName;
    private String originalName;
    private String contentType;
    private String fileUrl;
    private long size;
    private Instant uploadDate;
    @Field("filePurpose")
    private FilePurposeType filePurpose;
    private String ownerId;
    private boolean isDeleted = false;
}
