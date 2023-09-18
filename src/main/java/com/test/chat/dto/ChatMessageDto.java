package com.test.chat.dto;

import com.test.chat.common.code.MessageCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private MessageCode type;
    private String roomId;
    private String sender;
    private String message;
}
