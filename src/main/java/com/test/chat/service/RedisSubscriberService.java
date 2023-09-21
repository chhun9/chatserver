package com.test.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisSubscriberService implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatMessageDto chatMessageDto = objectMapper.readValue(publishMessage, ChatMessageDto.class);
            simpMessageSendingOperations.convertAndSend("/sub/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
