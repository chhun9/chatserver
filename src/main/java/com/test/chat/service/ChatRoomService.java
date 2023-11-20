package com.test.chat.service;

import com.test.chat.entity.ChatRoom;
import com.test.chat.entity.ChatUser;
import com.test.chat.entity.ChatUserChatRoom;
import com.test.chat.repository.ChatRoomRepository;
import com.test.chat.repository.ChatUserChatRoomRepository;
import com.test.chat.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatUserRepository chatUserRepository;
    private final ChatUserChatRoomRepository chatUserChatRoomRepository;

    public List<ChatRoom> getChatRoomByChatUser(){
        ChatUser loginChatUser = getLoginChatUser();
        return chatRoomRepository.findAllByChatUserChatRoomsIn(loginChatUser.getChatUserChatRooms());
    }
    public ChatRoom createChatRoom(String chatRoomName){
        if(!Objects.isNull(chatRoomRepository.findFirstByChatRoomName(chatRoomName))){
            throw new IllegalArgumentException("exist chat room");
        }

        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);

        ChatUser chatUser = getLoginChatUser();
        ChatUserChatRoom chatUserChatRoom = new ChatUserChatRoom();
        chatUser.addChatUserChatRooms(chatUserChatRoom);
        chatUserChatRoom.takeChatRoom(saveChatRoom);
        chatUserChatRoomRepository.save(chatUserChatRoom);

        return saveChatRoom;
    }

    private ChatUser getLoginChatUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return chatUserRepository.findFirstByChatUserId(userId);
    }
}
