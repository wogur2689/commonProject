package com.example.commonproject.login.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpDTO {
    private Long id;
    private String userId;      //아이디
    private String password;    //비밀번호
    private String name;        //이름
    private String email;       //이메일
    private String nickName;    //닉네임
    private String birthDate;   //생년월일(yyyy-mm-dd)
    private String phoneNumber; //전화번호(010-0000-0000)
    private String address;     //주소
    private String role;        //권한 (기본은 guest)
}
