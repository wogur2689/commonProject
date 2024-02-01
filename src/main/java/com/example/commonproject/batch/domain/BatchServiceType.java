package com.example.commonproject.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BatchServiceType {
    TEST("테스트 배치");

    private String serviceType;
}
