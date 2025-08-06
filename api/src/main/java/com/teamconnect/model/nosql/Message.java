package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(expiry = 0)
@EqualsAndHashCode(callSuper = true)
public class Message extends BaseCouchbaseModel{
    @Field(name = "channel_id")
    private String channelId;

    @Field(name = "author_id")
    private String authorId;

    @Field
    private String content;

    @Field
    private Instant timestamp;

    @Field(name = "edited_timestamp")
    private Instant editedTimestamp;

    @Field
    private Boolean tts;

    @Field(name = "mention_everyone")
    private Boolean mentionEveryone;

    @Field(name = "mention_user_ids")
    private List<String> mentionUserIds;

    @Field(name = "mention_role_ids")
    private List<String> mentionRoleIds;

    @Field(name = "attachments")
    private List<Attachment> attachments;

    @Field
    private List<Embed> embeds;

    @Field
    private List<Reaction> reactions;

    @Field
    private String nonce;

    @Field
    private Boolean pinned;

    @Field
    private String webhookId;

    @Field
    private Integer type;

    @Field(name = "message_reference")
    private MessageReference messageReference;

    @Field
    private Integer flags;

    @Field(name = "reference_message")
    private ReferencedMessage referencedMessage;
}
