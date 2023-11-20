package com.test.chat.repository;

import com.test.chat.entity.ChatUserChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserChatRoomRepository extends JpaRepository<ChatUserChatRoom, Long> {
}
