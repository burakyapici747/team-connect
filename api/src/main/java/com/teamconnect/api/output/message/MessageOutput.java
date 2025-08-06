package com.teamconnect.api.output.message;

import com.teamconnect.api.output.user.AuthorOutput;
import com.teamconnect.model.nosql.Attachment;
import com.teamconnect.model.nosql.Mention;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record MessageOutput(
    String id,
    String channelId,
    String content,
    Long timestamp,
    Instant editedTimestamp,
    Boolean pinned,
    Integer type,
    Set<Attachment> attachments,
    Set<Mention> mentions,
    List<Map<String, Object>> reactions,
    AuthorOutput author
) {}
