package com.example.commonproject.common.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtil {
    //응답결과 코드및 View 세팅
    public static void setViewReturn(ModelAndView mav, String code, String view) {
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName(view);
    }

    //응답결과 코드 및 jsonView 세팅
    public static void setJsonReturn(ModelAndView mav, String code) {
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName("jsonView");
    }
}
