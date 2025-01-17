package com.teamconnect.service.impl;

import com.teamconnect.dto.MessageDto;
import com.teamconnect.mapper.MessageMapper;
import com.teamconnect.model.nosql.Message;
import com.teamconnect.repository.couchbase.MessageRepository;
import com.teamconnect.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDto> getMessagesByChannelId(String channelId){
        List<Message> messageList = messageRepository.findMessageByChannelId(channelId);
        return MessageMapper.INSTANCE.messageListToMessageDtoList(messageList);
    }
}
