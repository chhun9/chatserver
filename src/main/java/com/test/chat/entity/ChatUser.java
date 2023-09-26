package com.test.chat.entity;

import com.test.chat.common.code.RoleCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String chatUserSeq;
    private String chatUserId;
    private String chatUserPw;
    private RoleCode roleCode;
}
