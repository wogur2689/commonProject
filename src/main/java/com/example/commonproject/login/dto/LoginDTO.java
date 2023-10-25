package com.example.commonproject.login.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDTO {
    private String userId;                       //userID
    private String password;                     //password
    private String role;                         //role
    private Collection<GrantedAuthority> token;  //token
}
