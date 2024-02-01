package com.example.commonproject.batch.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatchRequestDto {
    private String serviceType; //서비스타입
    private String batchLevel;  //배치 중요도(상중하)
    private String batchStatus; //상태값
    private String content;     //작업내용
    private String code;        //코드
    private String msg;         //메세지
}
