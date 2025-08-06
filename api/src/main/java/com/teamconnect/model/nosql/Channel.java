package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Channel extends BaseCouchbaseModel{
    private String guildId;

    private int type;

    private String name;

    private Integer position;

    private String topic;

    private Boolean nsfw;

    @Field(name = "last_message_id")
    private String lastMessageId;

    @Field(name = "rate_limit_per_user")
    private Integer rateLimitPerUser;

    @Field(name = "parent_id")
    private String parentId;

    private Integer bitrate;

    @Field(name = "user_limit")
    private Integer userLimit;

    private Set<String> recipients;
    private String icon;

    @Field(name = "owner_id")
    private String ownerId;

    @Field(name = "permission_overwrites")
    private Set<PermissionOverwrite> permissionOverwrites;

    @Field(name = "last_pin_timestamp")
    private Instant lastPinTimestamp;
}
