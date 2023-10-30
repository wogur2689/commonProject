package com.example.commonproject.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNET_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "시스템 에러입니다.");

    private final HttpStatus status;
    private final String message;
}
