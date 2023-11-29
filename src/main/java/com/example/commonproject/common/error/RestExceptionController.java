package com.example.commonproject.common.error;

import com.example.commonproject.common.dto.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class RestExceptionController {

    //커스텀 Exception
    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<CommonResponse<Void>> handleCommonExceptionHandler(CommonException e) {
        log.error("error : {}", e);

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(CommonResponse.error("9999", e.getErrorCode().getStatus().toString(), e.getErrorCode().getMessage()));
    }

    //기본 Exception
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponse<Void>> unhandledException(Exception e, HttpServletRequest request) {
        log.error("error : {}",e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonResponse.error("9999", HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()));
    }

    //vaild
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<CommonResponse<Void>>> argsValidHandler(MethodArgumentNotValidException e) {
        log.error("error : {}", e);

        List<CommonResponse<Void>> errors = new ArrayList<>();
        e.getFieldErrors().stream()
                .forEach(error -> errors.add(CommonResponse.error("9999", error.getField(), error.getDefaultMessage())));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
