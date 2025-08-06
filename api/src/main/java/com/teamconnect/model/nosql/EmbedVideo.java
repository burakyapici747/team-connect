package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmbedVideo {
    @Field
    private String url;

    @Field
    private Integer height;

    @Field
    private Integer width;
}
