package com.teamconnect.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange dmChannelDirectExchange(){
        return ExchangeBuilder.directExchange("dm.channel.message.exchange").durable(true).build();
    }

    @Bean
    public Queue dmChannelMessagesQueue(){
        return new Queue("dm.channel.message.queue", true);
    }

    @Bean
    public Binding dmChannelMessagesBinding(Queue dmChannelMessagesQueue, DirectExchange directExchange){
        return BindingBuilder.bind(dmChannelMessagesQueue)
            .to(directExchange)
            .with("dm.channel.message.#");
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
