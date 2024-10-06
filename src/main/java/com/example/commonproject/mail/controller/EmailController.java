package com.example.commonproject.mail.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/mail")
public class EmailController {
    @GetMapping
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("mail");
        return mav;
    }

    @GetMapping("/mailResult")
    public ModelAndView callBack(ModelAndView mav) {
        mav.setViewName("mailResult");
        return mav;
    }
}
