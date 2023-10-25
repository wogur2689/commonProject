//package com.example.commonproject.common.error;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Slf4j
//@Controller
//public class CommonErrorController implements ErrorController {
//
//    /**
//     * 페이지 에러 처리
//     */
//    @RequestMapping(path = "/error", produces = MediaType.TEXT_HTML_VALUE) // page 요청하는 과정에서 에러가 발생하는 경우 동작하는 함수
//    public ModelAndView errorPage(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
//        log.error("요청페이지 : {}", request.getRequestURI());
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        // error로 들어온 에러의 status를 불러온다 (ex:404,500,403...)
//
//        // status code, message 설정
//        mav.addObject("req", request.getRequestURI());
//        mav.addObject("code", status.toString());
//        mav.addObject("msg", "존재하지 않는 페이지입니다.");
//        mav.setViewName("common/error");
//        return mav;
//    }
//
//}
