package com.example.commonproject.login.config.handler;

import com.example.commonproject.login.dto.LoginDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

/**
 * 로그인 성공시 세션 생성
 */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //1. 세션 초기화
        HttpSession session = request.getSession(false);

        try {
            //2. id, 권한 추출
            String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
            Collection<GrantedAuthority> authority = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            //3. 유저 정보 세팅
            LoginDTO loginDTO = LoginDTO.builder()
                    .userId(username)
                    .password(password)
                    .token(authority)
                    .build();

            //4. 유저정보 세션에 저장 후 세션 지속시간 설정(60분)
            session.setAttribute("UserInfo", loginDTO);
            session.setMaxInactiveInterval(3600);
        } catch (Exception e) {
            log.error("### error : {} ###", e.getMessage());
        }
        //로그인 성공후 메인으로 이동
        log.info("### login success ###");
        response.sendRedirect(request.getContextPath() + "/");
    }
}
