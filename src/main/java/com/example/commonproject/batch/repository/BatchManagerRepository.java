package com.example.commonproject.batch.repository;

import com.example.commonproject.batch.domain.BatchManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchManagerRepository extends JpaRepository<BatchManager, Long> {
}
