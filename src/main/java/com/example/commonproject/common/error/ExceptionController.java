package com.example.commonproject.common.error;

import com.example.commonproject.common.util.ModelAndViewUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionController {
    //기본 Exception
    @ExceptionHandler(value = Exception.class)
    public ModelAndView unhandledException(Exception e) {
        log.error("error : {}", e);
        return ModelAndViewUtil.setJsonReturn("0003");
    }
}
