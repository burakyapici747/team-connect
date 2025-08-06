package com.teamconnect.dto;

import com.teamconnect.api.output.user.AuthorOutput;
import com.teamconnect.model.nosql.Attachment;
import com.teamconnect.model.nosql.Mention;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class WebSocketMessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String channelId;
    private String content;
    private Long timestamp;
    private Instant editedTimestamp;
    private Boolean pinned;
    private Integer type;
    private Set<Attachment> attachments;
    private Set<Mention> mentions;
    private List<Map<String, Object>> reactions;
    private AuthorOutput author;

    public WebSocketMessageDto() {
    }
}
