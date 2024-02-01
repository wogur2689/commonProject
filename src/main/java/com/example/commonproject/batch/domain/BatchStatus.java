package com.example.commonproject.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BatchStatus {
    REQUEST, PROCESSING, COMPLETE, ERROR;
}
