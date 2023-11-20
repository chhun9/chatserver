package com.test.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatRoomSeq;
    private String chatRoomName;

    @OneToMany(mappedBy = "chatRoom")
    @JsonIgnore
    private List<ChatUserChatRoom> chatUserChatRooms = new ArrayList<>();

    public ChatRoom(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }
}
