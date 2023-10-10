package com.example.commonproject.login.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * 로그인 실패시 로그인 페이지에 에러 메세지 전송.
 */
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //1. 실패한 원인 response에 할당
        log.info("### error msg : {} ###", exception.getMessage());
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,"비밀번호가 틀렸습니다. 다시 시도해 주세요.");

        //2. 로그인 화면으로 이동
        log.info("### login fail ###");
        response.sendRedirect("/login");
    }
}
