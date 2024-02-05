package com.example.commonproject.batch.dto;

import com.example.commonproject.batch.domain.BatchLevel;
import com.example.commonproject.batch.domain.BatchManager;
import com.example.commonproject.batch.domain.BatchServiceType;
import com.example.commonproject.batch.domain.BatchStatus;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatchResponseDto {
    private BatchServiceType serviceType; //서비스타입
    private BatchLevel batchLevel;  //배치 중요도(상중하)
    private BatchStatus batchStatus; //상태값
    private String content;     //작업내용
    private String code;        //코드
    private String msg;         //메세지

    //entity -> dto
    public static BatchResponseDto toDto(BatchManager batchManager) {
        return BatchResponseDto.builder()
                .serviceType(batchManager.getServiceType())
                .batchLevel(batchManager.getBatchLevel())
                .batchStatus(batchManager.getBatchStatus())
                .content(batchManager.getContent())
                .code(batchManager.getCode())
                .msg(batchManager.getMsg())
                .build();
    }
}
