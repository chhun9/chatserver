package com.test.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDto implements Serializable {
    private static final Long serialVersionUID = 922337236854775807L;
    private String roomId;
    private String name;

    public static ChatRoomDto create(String name) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setRoomId(UUID.randomUUID().toString());
        chatRoomDto.setName(name);
        return chatRoomDto;
    }
}