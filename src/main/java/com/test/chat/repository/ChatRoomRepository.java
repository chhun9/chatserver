package com.test.chat.repository;

import com.test.chat.dto.ChatRoomDto;
import com.test.chat.service.RedisSubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriberService redisSubscriberService;
    private static final String CHAT_ROOM = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoomDto> hashOperations;
    private Map<String, ChannelTopic> topicMap;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
        topicMap = new HashMap<>();
    }

    public List<ChatRoomDto> findAllRooms() {
        return hashOperations.values(CHAT_ROOM);
    }

    public ChatRoomDto findRoomById(String id) {
        return hashOperations.get(CHAT_ROOM, id);
    }

    /**
     * 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
     */
    public ChatRoomDto createChatRoom(String name) {
        ChatRoomDto chatRoom = ChatRoomDto.create(name);
        hashOperations.put(CHAT_ROOM, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    /**
     * 채팅방 입장 : redis에 topic을 만들고 pub/sub 통신을 하기 위해 리스너를 설정한다.
     */
    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topicMap.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListenerContainer.addMessageListener(redisSubscriberService, topic);
            topicMap.put(roomId, topic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return topicMap.get(roomId);
    }
}