package com.example.commonproject.login.Controller;

import com.example.commonproject.common.util.ResponseCode;
import com.example.commonproject.login.dto.SignUpDto;
import com.example.commonproject.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class LoginRestController {

    private final LoginService loginService;

    /**
     * 아이디, 닉네임 중복체크
     */
    @PostMapping("/duplicationIsCheck")
    public ModelAndView userDuplicationIsCheck(@RequestBody SignUpDto signUpDTO, BindingResult result, ModelAndView mav) {
        //추후 vaild추가
        //조회
        String code = loginService.userInfoDuplicationIsCheck(signUpDTO);

        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName("jsonView");
        return mav;
    }

    /**
     * 회원가입
     */
    @PostMapping("/signUp-processing")
    public ModelAndView signUpProcess(@RequestBody SignUpDto signUpDTO, BindingResult result, ModelAndView mav) {
        //추후 vaild추가
        //db 저장
        String code = loginService.insertUserInfo(signUpDTO);

        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName("jsonView");
        return mav;
    }

    /**
     * 패스워드 변경
     */
    @PostMapping("/password-change")
    public ModelAndView passwordChange(@RequestBody SignUpDto signUpDTO, BindingResult result, ModelAndView mav) {
        //추후 vaild추가

        //db 저장
        String code = loginService.updatePassword(signUpDTO);

        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName("jsonView");
        return mav;
    }

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
