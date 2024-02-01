package com.example.commonproject.batch.service;

import com.example.commonproject.batch.domain.BatchManager;
import com.example.commonproject.batch.dto.BatchRequestDto;
import com.example.commonproject.batch.repository.BatchManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BatchService {
    //메인 배치 서비스
    @Autowired
    private BatchManagerRepository batchManagerRepository;

    //배치 적재

    public Long BatchInsert(BatchRequestDto batchRequestDto) {
        BatchManager batchManager = batchRequestDto.toEntity();
        return batchManagerRepository.save(batchManager).getId();
    }

    //배치 업데이트
    public void BatchUpdate(BatchRequestDto batchRequestDto, Long id) {
        BatchManager batchManager = batchManagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("error "));

        //변경감지
        batchManager.batchStatusUpdate(batchRequestDto);
    }
}
