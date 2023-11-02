package com.example.commonproject.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
    CODE_1002("1002", "비밀번호가 틀렸습니다."),
    CODE_1003("1003", "이미 사용중인 아이디 입니다."),
    CODE_1004("1004", "이미 사용중인 닉네임 입니다."),
    CODE_1005("1005", "비밀번호는 8~15자까지 문자, 숫자, 특수문자만 가능합니다."),
    CODE_1006("1006", "회원가입에 실패하였습니다. 다시 시도해주세요.");

    //payment 2001 ~ 3000

    //chat 3001 ~ 4000

    private final String code;
    private final String msg;

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
