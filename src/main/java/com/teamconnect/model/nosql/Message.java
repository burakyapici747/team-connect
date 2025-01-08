package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.MessageType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Document
@TypeAlias("MESSAGE")
public class Message extends BaseCouchbaseModel{
    private String chatId;
    private String content;
    private String senderId;
    private MessageType type = MessageType.DEFAULT;
    private Instant editedAt;
    private Instant deletedAt;
    private String replyTo;
    private Set<Attachment> attachments = new HashSet<>();
    private Map<String, Set<String>> reactions = new HashMap<>();
    private MessageMention mentions;
}
