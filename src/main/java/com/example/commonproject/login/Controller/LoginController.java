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
    @GetMapping("/")
    public ModelAndView loginPage(ModelAndView mav) {
        log.info("로그인 페이지");
        mav.setViewName("login/login");
        return mav;
    }

}
