package com.test.chat.controller;

import com.test.chat.common.code.MessageCode;
import com.test.chat.dto.ChatMessageDto;
import com.test.chat.repository.ChatRoomRepository;
import com.test.chat.service.RedisPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisPublisherService redisPublisherService;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        if (MessageCode.ENTER.equals(message.getType())) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님 입장");
        }
        redisPublisherService.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}