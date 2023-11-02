package com.example.commonproject.login.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 기본 로그인 dto
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDTO {
    private String userId;                       //userID
    private String role;                         //role
}
