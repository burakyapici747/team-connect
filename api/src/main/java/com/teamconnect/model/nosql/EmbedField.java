package com.teamconnect.model.nosql;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmbedField {
    @Field
    private String name;

    @Field
    private String value;

    @Field
    private Boolean inline;
}
