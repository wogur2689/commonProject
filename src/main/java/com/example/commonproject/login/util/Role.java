package com.example.commonproject.login.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 권한
 */
@Getter
@AllArgsConstructor
public enum Role {
    GUEST("guest", "손님"),
    USER("user", "유저"),
    ADMIN("admin", "관리자");

    private final String role;
    private final String msg;
}
