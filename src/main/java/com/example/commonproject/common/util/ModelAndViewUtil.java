package com.example.commonproject.common.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtil {
    //응답결과 - 코드, View
    public static ModelAndView setViewReturn(String code, String view) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName(view);
        return mav;
    }

    //응답결과 - 코드, jsonView
    public static ModelAndView setJsonReturn(String code) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName("jsonView");
        return mav;
    }

    //응답결과 - 데이터, 코드, jsonView
    public static ModelAndView setJsonDataReturn(String code, String name, Object data) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.addObject(name, data);
        mav.setViewName("jsonView");
        return mav;
    }
}
