package com.teamconnect.service.impl;

import com.teamconnect.model.websocket.WebSocketMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisMessageService {
    private static final String OFFLINE_MESSAGES_KEY = "offline:messages:";
    private final RedisTemplate<String, WebSocketMessage> redisTemplate;

    public RedisMessageService(RedisTemplate<String, WebSocketMessage> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveOfflineMessage(String userId, WebSocketMessage message) {
        String key = OFFLINE_MESSAGES_KEY + userId;
        redisTemplate.opsForList().rightPush(key, message);
    }

    public List<WebSocketMessage> getAndRemoveOfflineMessages(String userId) {
        String key = OFFLINE_MESSAGES_KEY + userId;
        List<WebSocketMessage> messages = new ArrayList<>();
        
        WebSocketMessage message;
        while ((message = redisTemplate.opsForList().leftPop(key)) != null) {
            messages.add(message);
        }
        
        return messages;
    }

    public void removeAllOfflineMessages(String userId) {
        String key = OFFLINE_MESSAGES_KEY + userId;
        redisTemplate.delete(key);
    }
} 