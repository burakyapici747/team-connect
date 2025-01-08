package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.ChatType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document
@TypeAlias("CHAT")
public class Chat {
    private ChatType type;

    private String teamId;
    private String channelId;

    private Set<String> participants = new HashSet<>();
    private String chatName;

    private Instant lastActivityAt;
}
