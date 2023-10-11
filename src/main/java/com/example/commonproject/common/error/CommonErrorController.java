package com.example.commonproject.common.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice // 모든 controller에 대해서 적용하기 위한 설정
public class CommonErrorController implements ErrorController {

    /**
     * 페이지 에러 처리
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE) // page 요청하는 과정에서 에러가 발생하는 경우 동작하는 함수
    public ModelAndView errorPage(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
        log.error("요청페이지 : {}", request.getRequestURI());
        // status code, message 설정
        mav.addObject("req", request.getRequestURI());
        mav.addObject("code", response.getStatus());
        mav.addObject("msg", "존재하지 않는 페이지입니다.");
        mav.setViewName("common/error/error");
        return mav;
    }

}
