package com.test.chat.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleCode {
    ROLE_USER("USER"),ROLE_ADMIN("ADMIN"),ROLE_GUEST("GUEST");
    private String value;
}
