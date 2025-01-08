package com.teamconnect.model.nosql;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MessagePreview {
    private String id;
    private String content;
    private String senderId;
    private Instant sentAt;
}
