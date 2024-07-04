package com.example.commonproject.batch.repository;

import com.example.commonproject.batch.domain.BatchManager;
import com.example.commonproject.batch.domain.BatchServiceType;
import com.example.commonproject.batch.domain.BatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchManagerRepository extends JpaRepository<BatchManager, Long> {
    Optional<BatchManager> findByBatchStatusAndServiceType(BatchStatus batchStatus, BatchServiceType batchServiceType);
}
