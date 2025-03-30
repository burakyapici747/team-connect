package com.teamconnect.service.impl;

import com.teamconnect.configuration.RabbitMQConfig;
import com.teamconnect.dto.WebSocketMessageDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitConsumer {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RabbitAdmin rabbitAdmin;
    private final RabbitMQConfig rabbitMQConfig;

    private Map<String, SimpleMessageListenerContainer> activeListener = new HashMap<>();

    public RabbitConsumer(
        SimpMessagingTemplate simpMessagingTemplate,
        RabbitAdmin rabbitAdmin,
        RabbitMQConfig rabbitMQConfig
    ){
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitMQConfig = rabbitMQConfig;
    }

    public void createChannelListener(String channelId){
        Queue queue = rabbitMQConfig.createDMChannelQueue(channelId);

        if(activeListener.containsKey(channelId)) return;

        SimpleMessageListenerContainer container =
            new SimpleMessageListenerContainer(rabbitAdmin.getRabbitTemplate().getConnectionFactory());
        container.setQueueNames(queue.getName());
        container.setMessageListener(message -> {
            try {
                WebSocketMessageDto webSocketMessageDto = (WebSocketMessageDto) rabbitAdmin.getRabbitTemplate().getMessageConverter()
                        .fromMessage(message);
                simpMessagingTemplate.convertAndSend("/topic/dm.queue." + channelId, webSocketMessageDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        container.start();

        activeListener.put(channelId, container);
    }

    public void removeChannelListener(String channelId){
        SimpleMessageListenerContainer container = activeListener.get(channelId);
        if(container != null){
            container.stop();
            activeListener.remove(channelId);
            rabbitMQConfig.deleteChannelQueue(channelId);
        }
    }


//    private final SimpMessagingTemplate messagingTemplate;
//
//    public RabbitConsumer(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @RabbitListener(
//        bindings = @QueueBinding(
//            exchange = @Exchange(value = "dm.exchange.dccd525d-d909-4cec-99f4-28cb459eb9a5"),
//            value = @Queue("dm.queue.87393e0e-2305-4a11-8b30-e4d11ba4ba68"),
//            key = "87393e0e-2305-4a11-8b30-e4d11ba4ba68"
//        )
//    )
//    public void consumeMessage(
//        @Payload WebSocketMessageDto webSocketMessageDto,
//        @Header("channelId") String channelId
//    ) {
//        messagingTemplate.convertAndSend("/topic/dm.queue." + channelId, webSocketMessageDto);
//    }
}
