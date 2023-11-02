package com.example.commonproject.login.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 로그인
     */
    @GetMapping
    public ModelAndView loginPage(ModelAndView mav) {
        mav.setViewName("login/login");
        return mav;
    }

    /**
     * 회원가입
     */
    @GetMapping("/signUp")
    public ModelAndView signUpPage(ModelAndView mav) {
        mav.setViewName("login/signUp");
        return mav;
    }

    /**
     * 패스워드 변경
     */
    @GetMapping("/password-update")
    public ModelAndView passwordUpdatePage(ModelAndView mav) {
        mav.setViewName("login/passwordUpdate");
        return mav;
    }
}
