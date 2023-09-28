package com.test.chat.repository;

import com.test.chat.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    ChatUser findFirstByChatUserId(String id);
}
