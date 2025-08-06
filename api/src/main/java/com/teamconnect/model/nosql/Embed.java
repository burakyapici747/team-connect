package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Embed {
    @Field
    private String title;

    @Field
    private String type;

    @Field
    private String description;

    @Field
    private String url;

    @Field
    private Instant timestamp;

    @Field
    private Integer color;

    @Field
    private EmbedFooter footer;

    @Field
    private EmbedImage image;

    @Field
    private EmbedThumbnail thumbnail;

    @Field
    private EmbedVideo video;

    @Field
    private EmbedProvider provider;

    @Field
    private EmbedAuthor author;

    @Field
    private List<EmbedField> fields;
}
