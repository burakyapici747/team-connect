package com.teamconnect.configuration;

import com.teamconnect.model.websocket.WebSocketMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, WebSocketMessage> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, WebSocketMessage> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        
        // Key serializer
        template.setKeySerializer(new StringRedisSerializer());
        
        // Value serializer
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(WebSocketMessage.class));
        
        // Hash key serializer
        template.setHashKeySerializer(new StringRedisSerializer());
        
        // Hash value serializer
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(WebSocketMessage.class));
        
        template.afterPropertiesSet();
        
        return template;
    }
} 