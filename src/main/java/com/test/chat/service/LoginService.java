package com.test.chat.service;

import com.test.chat.common.code.RoleCode;
import com.test.chat.dto.ChatUserDto;
import com.test.chat.entity.ChatUser;
import com.test.chat.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public ChatUser save(ChatUserDto chatUserDto) {
        if (!Objects.isNull(chatUserRepository.findFirstByChatUserId(chatUserDto.getChatUserId()))) {
            throw new IllegalArgumentException("exist user");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ChatUser chatUser = new ChatUser(chatUserDto.getChatUserId(), encoder.encode(chatUserDto.getChatUserPw()), RoleCode.ROLE_USER);

        return chatUserRepository.save(chatUser);
    }
}
