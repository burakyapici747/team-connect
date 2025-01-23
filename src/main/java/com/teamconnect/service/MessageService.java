package com.teamconnect.service;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<MessageDto> getMessagesByChannelId(String channelId);
    MessageDto sendMessage(String channelId, String authorId,  MessageCreateInput messageCreateInput);
}
