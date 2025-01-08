package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.ChannelType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Document
@TypeAlias("CHANNEL")
public class Channel {
    private ChannelType channelType;
    private String teamId;
    private String name;
    private Instant lastMessageAt;
    private Set<String> participants = new HashSet<>();
    private Map<String, Boolean> settings = new HashMap<>();
    private List<MessagePreview> lastMessages = new ArrayList<>();
}
