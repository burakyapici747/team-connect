package com.teamconnect.mapper;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.api.output.MessageOutput;
import com.teamconnect.dto.MessageDto;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "pinned", constant = "false")
    @Mapping(target = "reactions", ignore = true)
    Message messageCreateInputToMessage(MessageCreateInput input);

    @Mapping(target = "reactions", source = "reactions")
    MessageDto messageToMessageDto(Message message);

    @Mapping(target = "reactions", source = "reactions")
    MessageOutput messageDtoToMessageOutput(MessageDto messageDto);

    List<MessageDto> messagesToMessageDtos(List<Message> messages);

    List<MessageOutput> messageDtosToMessageOutputs(List<MessageDto> messageDtos);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "reactionType", source = "reactionType")
    MessageDto.ReactionDto reactionToReactionDto(Message.Reaction reaction);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "reactionType", source = "reactionType")
    MessageOutput.ReactionOutput reactionDtoToReactionOutput(MessageDto.ReactionDto reactionDto);
}
