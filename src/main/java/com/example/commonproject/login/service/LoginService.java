package com.example.commonproject.login.service;

import com.example.commonproject.common.util.ResponseCode;
import com.example.commonproject.login.Entity.UserInfoEntity;
import com.example.commonproject.login.dto.SignUpDTO;
import com.example.commonproject.login.repository.LoginRepository;
import com.example.commonproject.login.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 유저 아이디 및 닉네임 중복 체크
     */
    public String userInfoDuplicationIsCheck(SignUpDTO signUpDTO) {
        //유저 아이디, 닉네임 조회
        Optional<UserInfoEntity> userInfoEntity = loginRepository.findByUserIdAndNickName(signUpDTO.getUserId(), signUpDTO.getNickName());
        //아이디 중복
        if(userInfoEntity.get().getUserId().equals(signUpDTO.getUserId())) {
            return ResponseCode.CODE_1003.getCode();
        }

        //닉네임 중복
        if(userInfoEntity.get().getNickName().equals(signUpDTO.getNickName())) {
            return ResponseCode.CODE_1004.getCode();
        }

        return ResponseCode.CODE_0000.getCode();
    }

    /**
     * 유저정보 저장
     */
    public String insertUserInfo(SignUpDTO signUpDTO) {
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

        //저장
        Long id = loginRepository.save(userInfoEntity).getId();

        if(id == null) return ResponseCode.CODE_1006.getCode();

        return ResponseCode.CODE_0000.getCode();
    }

    /**
     * 패스워드 변경
     */
    public String updatePassword(SignUpDTO signUpDTO) {
        //유저정보 조회
        UserInfoEntity userInfoEntity = loginRepository.findByUserId(signUpDTO.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 없는 유저입니다."));

        //변경감지를 이용한 비밀번호 변경후 저장
        userInfoEntity.updateNickname(signUpDTO.getNickName());

        return ResponseCode.CODE_0000.getCode();
    }
}
