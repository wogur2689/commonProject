package com.example.commonproject.common.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtil<T> {
    //페이지(view) 반환
    public ModelAndView returnMav(String dataName, T data, String viewName, String code) {
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject(dataName, data);
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        return mav;
    }

    //jsonView 반환
    public ModelAndView returnJsonMav(String dataName, T data, String code) {
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject(dataName, data);
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        return mav;
    }

    //응답결과 코드만 필요한 경우 사용
    public void setReturnCode(ModelAndView mav, String code) {
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
    }
}
