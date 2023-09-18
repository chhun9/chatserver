package com.test.chat.dto;

import com.test.chat.common.code.MessageCode;
import com.test.chat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoomDto {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDto(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessageDto chatMessageDto, ChatService chatService) {
        if (MessageCode.ENTER.equals(chatMessageDto.getType())) {
            sessions.add(session);
            chatMessageDto.setMessage(chatMessageDto.getSender() + "입장");
        }
        sendMessage(chatMessageDto, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.stream().forEach(session -> chatService.sendMessage(session, message));
    }
}