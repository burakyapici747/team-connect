package com.teamconnect.configuration;

import com.teamconnect.dto.WebSocketMessageDto;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class RabbitMQConfig {

    private static final String DM_CHANNEL_PREFIX = "dm.queue.";
    private static final String EXCHANGE_NAME = "dm.channel.messages.exchange";

    private final ConnectionFactory connectionFactory;
    private final SimpMessagingTemplate messagingTemplate;

    public RabbitMQConfig(ConnectionFactory connectionFactory, SimpMessagingTemplate messagingTemplate){
        this.connectionFactory = connectionFactory;
        this.messagingTemplate = messagingTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @PostConstruct
    public void init(){
        Exchange exchange = new TopicExchange(EXCHANGE_NAME);
        rabbitAdmin(connectionFactory).declareExchange(exchange);
    }

    public Queue createDMChannelQueue(String channelId) {
        String queueName = String.format("%s%s",
            DM_CHANNEL_PREFIX,
            channelId
        );

        Queue queue = QueueBuilder.durable(queueName)
            .withArgument("x-message-ttl", 7 * 24 * 60 * 60 * 1000)
            .withArgument("x-expires", 30 * 24 * 60 * 60 * 1000)
            .build();

        RabbitAdmin admin = rabbitAdmin(connectionFactory);
        admin.declareQueue(queue);

        Binding binding = BindingBuilder.bind(queue)
            .to(new TopicExchange(EXCHANGE_NAME))
            .with(channelId);
        admin.declareBinding(binding);

        return queue;
    }

    public void deleteChannelQueue(String channelId){
        String queueName = String.format("%s%s", DM_CHANNEL_PREFIX, channelId);
        rabbitAdmin(connectionFactory).deleteQueue(queueName);
    }

    public void sendChannelMessage(String channelId, WebSocketMessageDto message){
        rabbitTemplate(connectionFactory).convertAndSend(
            EXCHANGE_NAME,
            channelId,
            message,
            m -> {
                m.getMessageProperties().setHeader("channelId", channelId);
                return m;
            }
        );
    }

    public void declareQueue(Queue queue, RabbitAdmin rabbitAdmin) {
        if (queue == null || rabbitAdmin == null) {
            throw new IllegalArgumentException("Queue and RabbitAdmin cannot be null");
        }
        rabbitAdmin.declareQueue(queue);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
