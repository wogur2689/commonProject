package com.example.commonproject.login.service;

import com.example.commonproject.login.Entity.UserInfoEntity;
import com.example.commonproject.login.dto.SignUpDTO;
import com.example.commonproject.login.repository.LoginRepository;
import com.example.commonproject.login.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * 유저정보 저장
     */
    public Long insertUserInfo(SignUpDTO signUpDTO) {
        //권한 부여 및 password인코딩후 저장
        UserInfoEntity userInfoEntity = UserInfoEntity.builder()
                .userId(signUpDTO.getUserId())
                .name(signUpDTO.getName())
                .nickName(signUpDTO.getNickName())
                .email(signUpDTO.getEmail())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .birthDate(signUpDTO.getBirthDate())
                .address(signUpDTO.getAddress())
                .phoneNumber(signUpDTO.getPhoneNumber())
                .role(Role.USER.getRole())
                .build();

        return loginRepository.save(userInfoEntity).getId();
    }
}
