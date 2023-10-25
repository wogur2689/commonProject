package com.example.commonproject.login.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoEntity {
    private String userId;      //아이디
    private String password;    //비밀번호
    private String name;        //이름
    private String email;       //이메일
    private String birthDate;   //생년월일(yyyy-mm-dd)
    private String phoneNumber; //전화번호(010-0000-0000)
    private String address;     //주소
    private String role;        //권한 (기본은 guest)
}
