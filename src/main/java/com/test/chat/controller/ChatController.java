package com.test.chat.controller;

import com.test.chat.component.JwtTokenProvider;
import com.test.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message, @Header("token")String token) {
//        String nickName = jwtTokenProvider.getUserNameFromJwt(token);
//        if (MessageCode.ENTER.equals(message.getType())) {
//            chatRoomRepository.enterChatRoom(message.getRoomId());
//            message.setMessage(nickName + "님 입장");
//        }
//        redisPublisherService.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}