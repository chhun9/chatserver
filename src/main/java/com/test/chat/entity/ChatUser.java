package com.test.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.chat.common.code.RoleCode;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatUserSeq;
    private String chatUserId;
    private String chatUserPw;
    @Enumerated(EnumType.STRING)
    private RoleCode roleCode;

    @OneToMany(mappedBy = "chatUser")
    @JsonIgnore
    private List<ChatUserChatRoom> chatUserChatRooms = new ArrayList<>();

    public ChatUser(String chatUserId, String chatUserPw, RoleCode roleCode) {
        this.chatUserId = chatUserId;
        this.chatUserPw = chatUserPw;
        this.roleCode = roleCode;
    }

    public void addChatUserChatRooms(ChatUserChatRoom chatUserChatRoom) {
        chatUserChatRoom.setChatUser(this);
        this.chatUserChatRooms.add(chatUserChatRoom);
    }
}
