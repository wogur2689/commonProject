package com.example.commonproject.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//사용자 정보룰 가져옴
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
        // 데이터베이스에서 사용자 정보를 검색하고 CustomUserDetails 객체를 반환
        // 사용자 정보가 없을 경우 UsernameNotFoundException 예외를 던집니다.
    }
}
