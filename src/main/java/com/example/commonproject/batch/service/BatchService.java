package com.example.commonproject.batch.service;

import com.example.commonproject.batch.domain.BatchManager;
import com.example.commonproject.batch.domain.BatchServiceType;
import com.example.commonproject.batch.domain.BatchStatus;
import com.example.commonproject.batch.dto.BatchRequestDto;
import com.example.commonproject.batch.dto.BatchResponseDto;
import com.example.commonproject.batch.repository.BatchManagerRepository;
import com.example.commonproject.common.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BatchService {
    //메인 배치 서비스
    @Autowired
    private BatchManagerRepository batchManagerRepository;

    //배치 적재
    public BatchResponseDto BatchInsert(BatchRequestDto batchRequestDto) {
        BatchManager batchManager = BatchRequestDto.toEntity(batchRequestDto);
        batchManagerRepository.save(batchManager);
        return BatchResponseDto.toDto(batchManager);
    }

    //배치 업데이트(request DTO)
    public BatchResponseDto BatchUpdate(BatchRequestDto batchRequestDto, Long id) {
        BatchManager batchManager = batchManagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("error "));

        //변경감지
        batchManager.batchStatusUpdate(batchRequestDto);

        return BatchResponseDto.toDto(batchManager);
    }

    //배치 업데이트(request test)
    public BatchResponseDto BatchUpdate(BatchRequestDto batchRequestDto) {
        BatchManager batchManager = batchManagerRepository
                .findByBatchStatusAndServiceType(batchRequestDto.getBatchStatus(), BatchServiceType.TEST)
                .orElseThrow(() -> new RuntimeException("error "));

        batchManager.batchStatusUpdate(batchRequestDto);

        return BatchResponseDto.toDto(batchManager);
    }
}
