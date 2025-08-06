package com.teamconnect.service.impl;

import com.teamconnect.dto.WebSocketMessageDto;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private final SimpMessagingTemplate messagingTemplate;

    public RabbitMQConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(
        bindings = @QueueBinding(
            exchange = @Exchange(value = "dm.channel.message.topic.exchange"),
            value = @Queue("dm.channel.message.queue"),
            key = "dm.channel.message.#"
        )
    )
    public void consumeMessage(
        @Payload WebSocketMessageDto webSocketMessageDto,
        @Header("channelId") String channelId
    ) {
        messagingTemplate.convertAndSend("/topic/dm.queue." + channelId, webSocketMessageDto);
    }
}
