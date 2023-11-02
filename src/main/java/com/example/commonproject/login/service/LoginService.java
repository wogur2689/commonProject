package com.example.commonproject.login.service;

import com.example.commonproject.login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;

    /**
     * 유저정보 저장
     */
    public void insertUserInfo() {
        loginRepository.save()
    }
}
