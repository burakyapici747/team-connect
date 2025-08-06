package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageReference {
    @Field
    private String messageId;

    @Field
    private String channelId;

    @Field
    private String guildId;
}
