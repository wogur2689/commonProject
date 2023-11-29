package com.example.commonproject.common.error;

import com.example.commonproject.common.dto.CommonResponse;
import com.example.commonproject.common.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionController {
    //기본 Exception
    @ExceptionHandler(value = Exception.class)
    public ModelAndView unhandledException(Exception e, ModelAndView mav) {
        log.error("error : {}",e);

        mav.setViewName("jsonView");
        mav.addObject("status", ErrorCode.INTERNET_SERVER_ERROR.getStatus());
        mav.addObject("message", ErrorCode.INTERNET_SERVER_ERROR.getMessage());
        return mav;
    }
}
