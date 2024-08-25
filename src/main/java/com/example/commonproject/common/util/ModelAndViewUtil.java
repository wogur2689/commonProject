package com.example.commonproject.common.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtil {
    //응답결과 코드및 View 세팅
    public static ModelAndView setViewReturn(String code, String view) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName(view);
        return mav;
    }

    //응답결과 코드 및 jsonView 세팅
    public static ModelAndView setJsonReturn(String code) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName("jsonView");
        return mav;
    }
}
