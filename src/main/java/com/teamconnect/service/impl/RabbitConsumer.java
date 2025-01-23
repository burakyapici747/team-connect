package com.teamconnect.service.impl;

import com.teamconnect.model.nosql.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {
    private final SimpMessagingTemplate messagingTemplate;

    public RabbitConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(
        bindings = @QueueBinding(
            exchange = @Exchange(value = "dm.exchange.dccd525d-d909-4cec-99f4-28cb459eb9a5"),
            value = @Queue,
            key = "#"
        )
    )
    public void consumeMessage(Message message, @Header("channelId") String channelId) {
        messagingTemplate.convertAndSend("/topic/dm.queue." + channelId, message);
    }
}
