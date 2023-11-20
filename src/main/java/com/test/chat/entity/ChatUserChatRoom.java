package com.test.chat.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ChatUserChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatUserChatRoomSeq;

    @ManyToOne
    @JoinColumn(name = "CHAT_USER_SEQ")
    private ChatUser chatUser;

    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_SEQ")
    private ChatRoom chatRoom;

    @CreationTimestamp
    private LocalDateTime createTime;
    public void takeChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.getChatUserChatRooms().add(this);
    }
}
