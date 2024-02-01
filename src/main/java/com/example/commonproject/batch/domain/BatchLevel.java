package com.example.commonproject.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BatchLevel {
    UPPER("상"),
    MIDDLE("중"),
    LOWER("하");

    private String batchLevel;
}
