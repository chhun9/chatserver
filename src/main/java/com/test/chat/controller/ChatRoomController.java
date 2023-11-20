package com.test.chat.controller;

import com.test.chat.component.JwtTokenProvider;
import com.test.chat.entity.ChatRoom;
import com.test.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> rooms() {
        return chatRoomService.getChatRoomByChatUser();
    }
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String chatRoomName){
        return chatRoomService.createChatRoom(chatRoomName);
    }
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoomDto roomInfo(@PathVariable String roomId){
//        return chatRoomRepository.findRoomById(roomId);
//    }
//
//    @GetMapping("/user")
//    @ResponseBody
//    public LoginInfoDto getUserInfo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        return LoginInfoDto.builder().name(name).token(jwtTokenProvider.generateToken(name)).build();
//    }
}
