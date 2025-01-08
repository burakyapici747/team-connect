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
    
    private static final String PRIVATE_CHAT_PREFIX = "private-chat";
    private static final String TEAM_CHAT_PREFIX = "team-chat";
    
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    public Queue createPrivateChatQueue(Long user1Id, Long user2Id) {
        if (user1Id == null || user2Id == null) {
            throw new IllegalArgumentException("User IDs cannot be null");
        }
        if (user1Id.equals(user2Id)) {
            throw new IllegalArgumentException("Cannot create chat queue for same user");
        }
        
        String queueName = String.format("%s.%d.%d", 
            PRIVATE_CHAT_PREFIX,
            Math.min(user1Id, user2Id), 
            Math.max(user1Id, user2Id)
        );
        
        return QueueBuilder.durable(queueName)
            .withArgument("x-message-ttl", 7 * 24 * 60 * 60 * 1000) // 7 days TTL
            .withArgument("x-expires", 30 * 24 * 60 * 60 * 1000)    // delete after 30 days of inactivity
            .build();
    }

    public Queue createTeamChatQueue(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("Team ID cannot be null");
        }
        
        String queueName = String.format("%s.%d", TEAM_CHAT_PREFIX, teamId);
        
        return QueueBuilder.durable(queueName)
            .withArgument("x-message-ttl", 30 * 24 * 60 * 60 * 1000) // 30 days TTL
            .build();
    }

    public void declareQueue(Queue queue, RabbitAdmin rabbitAdmin) {
        if (queue == null || rabbitAdmin == null) {
            throw new IllegalArgumentException("Queue and RabbitAdmin cannot be null");
        }
        rabbitAdmin.declareQueue(queue);
    }
}
