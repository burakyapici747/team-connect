package com.teamconnect.mapper;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.common.enumerator.MessageTargetType;
import com.teamconnect.common.enumerator.WebSocketMessageType;
import com.teamconnect.model.websocket.WebSocketMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WebSocketMessageMapper {
    WebSocketMessageMapper INSTANCE = Mappers.getMapper(WebSocketMessageMapper.class);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "senderId", source = "senderId")
    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
    WebSocketMessage enrichWebSocketMessage(WebSocketMessage message, String senderId);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "content", source = "content")
    @Mapping(target = "receiverId", source = "receiverId")
    @Mapping(target = "webSocketMessageType", ignore = true)
    @Mapping(target = "senderId", ignore = true)
    @Mapping(target = "metadata", ignore = true)
    WebSocketMessage messageCreateInputToWebSocketMessage(MessageCreateInput input);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "receiverId", source = "receiverId")
    @Mapping(target = "messageTargetType", ignore = true)
    @Mapping(target = "parentMessageId", ignore = true)
    @Mapping(target = "fileIds", ignore = true)
    MessageCreateInput webSocketMessageToMessageCreateInput(WebSocketMessage message);

    default WebSocketMessageType mapMessageTargetType(MessageTargetType targetType) {
        if (targetType == null) return null;
        return switch (targetType) {
            case USER -> WebSocketMessageType.CHAT;
            case TEAM -> WebSocketMessageType.GROUP_CHAT;
        };
    }
} 