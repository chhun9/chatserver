package com.test.chat.controller;

import com.test.chat.component.JwtTokenProvider;
import com.test.chat.dto.ChatRoomDto;
import com.test.chat.dto.LoginInfoDto;
import com.test.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomDto> rooms() {
        return chatRoomRepository.findAllRooms();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDto createRoom(@RequestParam String name){
        return chatRoomRepository.createChatRoom(name);
    }
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomDto roomInfo(@PathVariable String roomId){
        return chatRoomRepository.findRoomById(roomId);
    }

    @GetMapping("/user")
    @ResponseBody
    public LoginInfoDto getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return LoginInfoDto.builder().name(name).token(jwtTokenProvider.generateToken(name)).build();
    }
}
