package com.example.commonproject.common.util;

import lombok.Getter;

@Getter
public enum ResponseCode {
    /**
     * api 통신 코드
     * 0000 ~ 9999
     */
    //common 0000 ~ 9999
    CODE_0000("0000", "성공"),
    CODE_0001("0001", "파라미터 검증 필요"),
    CODE_0002("0002", "시간 초과"),
    CODE_0003("0003", "네트워크 에러"),
    CODE_0004("0004", "시스템 에러"),
    CODE_0005("0005", "기타 에러"),

    //login 1001 ~ 2000
    CODE_1001("1001", "존재하지 않는 아이디입니다."),
    CODE_1002("1002", "비밀번호가 틀렸습니다.");

    //payment 2001 ~ 3000

    //chat 3001 ~ 4000

    private final String code;
    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMessage(String code) {
        if(code == null) {
            return "해당 코드는 없는 코드입니다.";
        }
        for(ResponseCode codeValue : values()) {
            if(codeValue.getCode().equals(code)) {
                return codeValue.getMsg();
            }
        }
        return "해당 코드는 없는 코드입니다.";
    }
}
