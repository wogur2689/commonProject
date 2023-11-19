package com.example.commonproject.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private String status;  //http 상태값
    private String code;    //api 공통코드
    private String message; //결과 메세지
    private T data;         //결과 데이터

    public static <T> CommonResponse<T> success(String code, String message, T data){
        return new CommonResponse<>(String.valueOf(HttpStatus.OK.value()), code, message, data);
    }

    public static CommonResponse<Void> error(String code, String status, String message){
        return new CommonResponse<>(code, status, message, null);
    }
}

