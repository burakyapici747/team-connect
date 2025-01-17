package com.teamconnect.mapper;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.api.output.MessageOutput;
import com.teamconnect.dto.MessageDto;

import java.util.List;

import com.teamconnect.model.nosql.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Message messageCreateInputToMessage(MessageCreateInput input);

    MessageDto messageToMessageDto(Message message);

    MessageOutput messageDtoToMessageOutput(MessageDto messageDto);

    List<MessageDto> messageListToMessageDtoList(List<Message> messages);

    List<MessageOutput> messageDtoListToMessageOutputList(List<MessageDto> messageDtos);
}
