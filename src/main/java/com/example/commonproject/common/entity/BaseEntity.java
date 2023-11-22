package com.example.commonproject.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; //생성일email

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAy; //수정일

    @Column(name = "created_by", length = 10, nullable = true)
    private String createdBy; // 작성자

    @Column(name = "updated_by", length = 10, nullable = true)
    private String updatedBy; // 수정자
}
