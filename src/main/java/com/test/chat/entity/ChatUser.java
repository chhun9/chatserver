package com.test.chat.entity;

import com.test.chat.common.code.RoleCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatUserSeq;
    private String chatUserId;
    private String chatUserPw;
    @Enumerated(EnumType.STRING)
    private RoleCode roleCode;
}
