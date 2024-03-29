package com.example.commonproject.batch.dto;

import com.example.commonproject.batch.domain.BatchLevel;
import com.example.commonproject.batch.domain.BatchManager;
import com.example.commonproject.batch.domain.BatchServiceType;
import com.example.commonproject.batch.domain.BatchStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatchRequestDto {
    private BatchServiceType serviceType; //서비스타입
    private BatchLevel batchLevel;  //배치 중요도(상중하)
    private BatchStatus batchStatus; //상태값
    private String content;     //작업내용
    private String code;        //코드
    private String msg;         //메세지

    //dto -> entity
    public static BatchManager toEntity(BatchRequestDto batchRequestDto) {
        return BatchManager.builder()
                .serviceType(batchRequestDto.getServiceType())
                .batchLevel(batchRequestDto.getBatchLevel())
                .batchStatus(batchRequestDto.getBatchStatus())
                .content(batchRequestDto.getContent())
                .code(batchRequestDto.getCode())
                .msg(batchRequestDto.getMsg())
                .build();
    }
}
