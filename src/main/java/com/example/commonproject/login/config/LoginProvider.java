package com.example.commonproject.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
/**
 * 인증 토큰 생성
 */
@Slf4j
public class LoginProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //1. 사용자 이름, 비밀번호 생성
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        //2. 유저 권한 생성
        Collection<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority("admin"));

        //3. 아이디, 비밀번호가 맞는지 틀리는지 확인
//        if(!username.equals(PropertiesValue.username) || !password.equals(PropertiesValue.password)) {
//            log.error("### login param error userId : {}, password : {}", username, password);
//            throw new BadCredentialsException(username);
//        }

        //4. 토큰 생성
        return new UsernamePasswordAuthenticationToken(username, password, authority);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
