package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmbedFooter {
    @Field
    private String text;

    @Field
    private String iconUrl;

    @Field
    private String proxyIconUrl;
}

