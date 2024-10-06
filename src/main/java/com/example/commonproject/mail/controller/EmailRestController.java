package com.example.commonproject.mail.controller;

import com.example.commonproject.common.util.ResponseCode;
import com.example.commonproject.mail.dto.EmailDto;
import com.example.commonproject.mail.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mail/")
public class EmailRestController {

    private final EmailService emailService;

    /**
     * 메일 보내기
     */
    @PostMapping("/mailSend")
    public ModelAndView mailSendPost(ModelAndView mav, HttpServletRequest request, BindingResult result) {
        String code = "0000";

        //1. 요청값 검증
        if(result.hasErrors()) {
            log.error("요청값 에러 : {}", result.getTarget());
            code = "6501";
        }
        else {
            EmailDto emailDto = EmailDto.builder()
                    .toUser(request.getParameter("toUser"))
                    .ccUser(request.getParameter("ccUser"))
                    .bccUser(request.getParameter("bccUser"))
                    .fromAddress(request.getParameter("fromAddress"))
                    .subject(request.getParameter("subject"))
                    .content(request.getParameter("content"))
                    .build();
            //2. 메일 전송
            code = emailService.sendMailSimple(emailDto);
        }

        //3, 결과 반환
        mav.addObject("code", code);
        mav.addObject("msg", ResponseCode.getMessage(code));
        mav.setViewName("mailResult");
        return mav;
    }
}
