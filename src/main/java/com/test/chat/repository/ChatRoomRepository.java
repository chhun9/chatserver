package com.test.chat.repository;

import com.test.chat.entity.ChatRoom;
import com.test.chat.entity.ChatUserChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findFirstByChatRoomName(String chatRoomName);
    List<ChatRoom> findAll();
    List<ChatRoom> findAllByChatUserChatRoomsIn(List<ChatUserChatRoom> chatUserChatRooms);
}
