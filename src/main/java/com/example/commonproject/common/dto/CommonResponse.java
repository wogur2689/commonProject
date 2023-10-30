package com.example.commonproject.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private String status;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(String message, T data){
        return new CommonResponse<>(String.valueOf(HttpStatus.OK.value()), message, data);
    }

    public static CommonResponse<Void> error(String status, String message){
        return new CommonResponse<>(status, message, null);
    }
}

