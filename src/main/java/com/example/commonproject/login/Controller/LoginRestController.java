package com.example.commonproject.login.Controller;

import com.example.commonproject.common.dto.CommonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginRestController {
    //소셜 로그인 및 그외의 api처리
    //네이버 로그인
    @GetMapping("/login/naver")
//    @Operation(summary = "네이버 로그인")
    public void naverLogin(//@Parameter(description = "네이버에서 받은 인증 코드", example = "")
                           @RequestParam String code) {

        //return CommonResponse.success("네이버 로그인 성공", userService.login(code));
    }
    //카카오 로그인
    @GetMapping("/login/kakao")
//    @Operation(summary = "카카오 로그인")
    public void kakaoLogin(//@Parameter(description = "카카오에서 받은 인증 코드", example = "")
            @RequestParam String code) {

        //return CommonResponse.success("카카오 로그인 성공", userService.login(code));
    }
    //구글 로그인
}
