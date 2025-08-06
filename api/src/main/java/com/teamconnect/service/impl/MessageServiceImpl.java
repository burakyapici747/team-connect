package com.teamconnect.service.impl;

import com.teamconnect.dto.MessageDto;
import com.teamconnect.service.MessageService;
import org.springframework.stereotype.Service;
import com.teamconnect.api.input.message.MessageCreateInput;

import java.util.List;
import java.util.UUID;
import java.io.IOException;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public MessageDto getMessageByChannelIdAndId(UUID channelId, UUID messageId) {
        return null;
    }

    @Override
    public List<MessageDto> getMessages(UUID channelId, UUID before, UUID after, int limit) {
        return List.of();
    }

    @Override
    public MessageDto sendMessage(UUID channelId, UUID authorId, MessageCreateInput messageCreateInput) throws IOException {

        return null;
    }
}
