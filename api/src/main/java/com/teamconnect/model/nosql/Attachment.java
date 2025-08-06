package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.FileType;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Field
    private UUID id;

    @Field(name = "file_name")
    private String fileName;

    @Field
    private String title;

    @Field
    private String description;

    @Field(name = "content_type")
    private String contentType;

    @Field
    private Long size;

    @Field
    private String url;

    @Field
    private Integer height;

    @Field
    private Integer width;

    @Field
    private Boolean ephemeral;

    @Field(name = "duration_secs")
    private Integer durationSecs;

    @Field
    private String waveform;

    @Field
    private Integer flags;

    @Field
    private FileType type;
}
