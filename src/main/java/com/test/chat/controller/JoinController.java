package com.test.chat.controller;

import com.test.chat.dto.ChatUserDto;
import com.test.chat.entity.ChatUser;
import com.test.chat.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {
    private final LoginService loginService;

    @PostMapping("/user")
    public ResponseEntity<ChatUser> signup(@RequestBody ChatUserDto chatUserDto) {
        return ResponseEntity.ok(loginService.save(chatUserDto));
    }
}
