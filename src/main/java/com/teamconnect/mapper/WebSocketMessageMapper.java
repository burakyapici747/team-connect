package com.teamconnect.mapper;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.model.websocket.WebSocketMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WebSocketMessageMapper {
    WebSocketMessageMapper INSTANCE = Mappers.getMapper(WebSocketMessageMapper.class);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "authorId", source = "authorId")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
    WebSocketMessage enrichWebSocketMessage(WebSocketMessage message, String authorId);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "content", source = "content")
    @Mapping(target = "channelId", source = "channelId")
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "metadata", ignore = true)
    WebSocketMessage messageCreateInputToWebSocketMessage(MessageCreateInput input);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "channelId", source = "channelId")
    @Mapping(target = "parentMessageId", ignore = true)
    MessageCreateInput webSocketMessageToMessageCreateInput(WebSocketMessage message);
}
