package com.example.commonproject.common.error;

import com.example.commonproject.common.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionController {
    //기본 Exception
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponse<Void>> unhandledException(Exception e) {
        log.error("error occur {}",e);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.error("9999", HttpStatus.NOT_FOUND.toString(), e.getMessage()));
    }
}
