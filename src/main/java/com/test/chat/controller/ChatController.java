package com.test.chat.controller;

import com.test.chat.common.code.MessageCode;
import com.test.chat.component.JwtTokenProvider;
import com.test.chat.dto.ChatMessageDto;
import com.test.chat.repository.ChatRoomRepository;
import com.test.chat.service.RedisPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisPublisherService redisPublisherService;
    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message, @Header("token")String token) {
        String nickName = jwtTokenProvider.getUserNameFromJwt(token);
        if (MessageCode.ENTER.equals(message.getType())) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(nickName + "님 입장");
        }
        redisPublisherService.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}