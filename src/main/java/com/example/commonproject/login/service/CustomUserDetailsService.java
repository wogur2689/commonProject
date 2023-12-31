package com.example.commonproject.login.service;

import com.example.commonproject.login.Entity.UserInfoEntity;
import com.example.commonproject.login.dto.CustomUserDetails;
import com.example.commonproject.login.repository.LoginRepository;
import com.example.commonproject.login.util.Role;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

//사용자 정보룰 가져옴
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //1. db에서 사용자 검색
        Optional<UserInfoEntity> userInfo = Optional.ofNullable(loginRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디는 없는 아이디입니다.")));

        //2. 유저 권한 생성
        Collection<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(Role.USER.getRole()));

        //3. UserDetails에 저장
        return CustomUserDetails.builder()
                .username(userInfo.get().getUserId())
                .password(userInfo.get().getPassword())
                .authorities(authority)
                .build();
    }
}
