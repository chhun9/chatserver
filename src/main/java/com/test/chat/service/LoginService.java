package com.test.chat.service;

import com.test.chat.entity.ChatUser;
import com.test.chat.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final ChatUserRepository chatUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ChatUser chatUser = chatUserRepository.findFirstByChatUserId(username);
        if (Objects.isNull(chatUser)) {
            throw new IllegalArgumentException("not exist user");
        }
        return new User(chatUser.getChatUserId(), chatUser.getChatUserPw(), Arrays.asList(new SimpleGrantedAuthority(chatUser.getRoleCode().toString())));
    }
}