package com.example.commonproject.common.error;

import com.example.commonproject.common.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public CommonException(ErrorCode code){
        this.errorCode = code;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if(this.message == null){
            return this.errorCode.getMessage();
        }
        return message;
    }
}

