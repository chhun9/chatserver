package com.test.chat.controller;

import com.test.chat.dto.ChatRoomDto;
import com.test.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }
    @GetMapping
    public List<ChatRoomDto> findAllRooms(){
        return chatService.findAllRooms();
    }
}
