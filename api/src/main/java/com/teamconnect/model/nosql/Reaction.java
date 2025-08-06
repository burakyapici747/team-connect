package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {
    @Field
    private Emoji emoji;

    @Field
    private Integer count;

    @Field
    private Boolean me;
}
