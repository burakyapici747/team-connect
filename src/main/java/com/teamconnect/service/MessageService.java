package com.teamconnect.service;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    MessageDto createMessage(MessageCreateInput input, String senderId);
    MessageDto getMessage(String messageId);
    List<MessageDto> getMessagesByReceiverId(String receiverId, Long timestamp, int limit);
    List<MessageDto> getMessagesBySenderId(String senderId, Long timestamp, int limit);
    void deleteMessage(String messageId, String userId);
    List<MessageDto> searchMessages(String userId, String query, int limit);
    Long getUnreadMessageCount(String userId);
}
