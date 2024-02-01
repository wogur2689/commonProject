package com.example.commonproject.batch.service;

import com.example.commonproject.batch.domain.BatchManager;
import com.example.commonproject.batch.dto.BatchRequestDto;
import com.example.commonproject.batch.repository.BatchManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchService {
    //메인 배치 서비스
    private BatchManagerRepository batchManagerRepository;

    //배치 적재
    @Transactional
    public void BatchInsert(BatchRequestDto batchRequestDto) {
        BatchManager batchManager = BatchRequestDto
        batchManagerRepository.save();
    }
}
