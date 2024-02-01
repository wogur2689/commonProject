package com.example.commonproject.batch.domain;

import com.example.commonproject.batch.dto.BatchRequestDto;
import com.example.commonproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 배치 매니저 entity
 */
@Getter
@Entity
@Table(name = "batch_manager")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatchManager extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_type")
    private String serviceType; //배치돌릴 서비스 타입

    @Column(name = "batch_level")
    private String batchLevel;  //배치 중요도(상중하)

    @Column(name = "batch_status")
    private String batchStatus; //상태값

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;     //작업내용

    @Column(name = "code")
    private String code;     //상태코드

    @Column(name = "msg")
    private String msg;     //상태메세지

    //변경감지(배치 대기 및 적재)
    public void batchReadyUpdate(BatchRequestDto batchRequestDto) {
        this.serviceType = batchRequestDto.getServiceType();
        this.batchLevel = batchRequestDto.getBatchLevel();
        this.batchStatus = batchRequestDto.getBatchStatus();
        this.content = batchRequestDto.getContent();
    }
    
    //변경감지(진행, 완료, 실패)
    public void batchProcessingUpdate(BatchRequestDto batchRequestDto) {
        this.serviceType = batchRequestDto.getServiceType();
        this.batchStatus = batchRequestDto.getBatchStatus();
        this.code = batchRequestDto.getCode();
        this.msg = batchRequestDto.getMsg();
    }
}

