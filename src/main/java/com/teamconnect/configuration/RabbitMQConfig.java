package com.teamconnect.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String DM_CHANNEL_PREFIX = "dm.queue.";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    public Queue createDMChannelQueue(String channelId) {
        String queueName = String.format("%s%s",
            DM_CHANNEL_PREFIX,
            channelId
        );

        return QueueBuilder.durable(queueName)
            .withArgument("x-message-ttl", 7 * 24 * 60 * 60 * 1000)
            .withArgument("x-expires", 30 * 24 * 60 * 60 * 1000)
            .build();
    }

    public void declareQueue(Queue queue, RabbitAdmin rabbitAdmin) {
        if (queue == null || rabbitAdmin == null) {
            throw new IllegalArgumentException("Queue and RabbitAdmin cannot be null");
        }
        rabbitAdmin.declareQueue(queue);
    }
}
