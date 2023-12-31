package com.example.commonproject.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class MainController {

    /*
     * home
     */
    @GetMapping("/")
    public ModelAndView home(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    /*
     * calculator
     */
    @GetMapping("/main/calculator")
    public ModelAndView calculator(ModelAndView mav) {
        mav.setViewName("main/calculator");
        return mav;
    }
}
