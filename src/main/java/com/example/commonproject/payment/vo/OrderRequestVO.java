package com.example.commonproject.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * VO는 값을 나타내는 객체로, 불변(immutable)하며 값이 변경되지 않아야 합니다. 예를 들어, 자바에서는 VO를 다음과 같이 구현할 수 있습니다:
 * 여기서는 주로 프론트에서 입력받은 값을 저장.
 */
@Getter
@AllArgsConstructor
public class OrderRequestVO {
    private String payMethod;  // 결제수단
    private String price;      // 결제상품금액
    private String buyerName;  // 구매자명
    private String buyerTel;   // 구매자연락처
    private String buyerEmail; // 구매자메일주소
}
