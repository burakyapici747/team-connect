package com.teamconnect.service;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface MessageService {
    List<MessageDto> getMessages(String channelId, String before, String after, int limit);

    MessageDto sendMessage(String channelId, String authorId, MessageCreateInput messageCreateInput) throws IOException;
}
