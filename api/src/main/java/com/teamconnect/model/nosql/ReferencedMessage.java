package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferencedMessage extends BaseCouchbaseModel{
    @Field
    private String channelId;

    @Field
    private String authorId;

    @Field
    private String content;

    @Field
    private Instant timestamp;

    @Field
    private Instant editedTimestamp;

    @Field
    private List<Attachment> attachments;

    @Field
    private List<Embed> embeds;

    @Field
    private List<Reaction> reactions;
}
