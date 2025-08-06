package com.teamconnect.service;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public interface MessageService {
    MessageDto getMessageByChannelIdAndId(UUID channelId, UUID messageId);
    List<MessageDto> getMessages(UUID channelId, UUID before, UUID after, int limit);
    MessageDto sendMessage(UUID channelId, UUID authorId, MessageCreateInput messageCreateInput) throws IOException;
}
