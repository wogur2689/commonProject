package com.example.commonproject.product.domain;

import lombok.*;

/**
 * 상품상태 메세지 (1: 준비, 2: 판매중, 3: 매진)
 */
@Getter
@AllArgsConstructor
public enum ProductStatus {

    PREPARING("1", "상품 준비 중입니다."),
    SALE("2", "판매 중입니다."),
    SOLD_OUT("3", "해당 상품은 매진되었습니다.");

    private final String code;
    private final String statusMsg;

    public static ProductStatus getStatus(String code) {
        switch (code.toUpperCase()) {
            case "PREPARING" -> {
                return PREPARING;
            }
            case "SALE" -> {
                return SALE;
            }
            case "SOLD_OUT" -> {
                return SOLD_OUT;
            }
            default -> {
                return null;
            }
        }
    }
}
