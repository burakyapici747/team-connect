package com.teamconnect.service.impl;

import com.teamconnect.dto.WebSocketMessageDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DMChannelMessageListener {
    private final SimpMessagingTemplate messagingTemplate;

    public DMChannelMessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "#{@dmChannelMessagesQueue.name}")
    public void consumeMessage(WebSocketMessageDto webSocketMessageDto, Message amqpMessage){
        try{
            String routingKey = amqpMessage.getMessageProperties().getReceivedRoutingKey();
            String channelId = extractChannelId(routingKey);

            if(Objects.nonNull(channelId)){
                messagingTemplate.convertAndSend(
                    "/topic/dm.channel.message.queue." + channelId,
                    webSocketMessageDto
                );
            }else{
                System.out.println("Could not extract channelId from routing key: {}" + routingKey);
            }


        }catch (Exception e){
            System.out.println(e);
        }
    }

    private String extractChannelId(String routingKey) {
        if (routingKey != null && routingKey.startsWith("dm.channel.message.route.")) {
            return routingKey.substring("dm.channel.message.route.".length());
        }
        return null;
    }
}
