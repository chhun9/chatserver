package com.test.chat.service;

import com.test.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisherService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic channelTopic, ChatMessageDto chatMessageDto) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessageDto);
    }
}
