package com.example.commonproject.payment.dto;

/**
 * DTO는 데이터 전송 객체를 나타내며,
 * 주로 데이터를 서로 다른 레이어나 컴포넌트 간에 전송할 때 사용됩니다.
 * DTO는 데이터를 캡슐화하고 전송에 필요한 필드 및 메서드를 가집니다. 예를 들어, Java에서 DTO는 다음과 같이 구현될 수 있습니다:
 * java
 */

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 도메인 객체는 비즈니스 도메인에서 발생하는 개념을 나타내는 객체입니다.
 * 도메인 객체는 비즈니스 규칙 및 도메인 로직을 포함할 수 있습니다.
 * 예를 들어, 은행 애플리케이션에서 계좌 도메인 객체는 다음과 같이 구현될 수 있습니다:
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDTO {
    private String payMethod;  // 결제수단
    private String merchantKey;// 상점 키
    private String merchantID; // 상점 id
    private String price;      // 결제상품금액
    private String buyerName;  // 구매자명
    private String buyerTel;   // 구매자연락처
    private String buyerEmail; // 구매자메일주소
    private String moid;       // 상품주문번호
    private String returnURL;  // 결과페이지(절대경로)
    private String ediDate;    // 요청시간
    private String signData;   //위변조 검증 데이터

    @Builder
    public OrderDTO(String payMethod, String merchantKey, String merchantID, String price, String buyerName, String buyerTel, String buyerEmail, String moid, String returnURL, String ediDate, String signData) {
        this.payMethod = payMethod;
        this.merchantKey = merchantKey;
        this.merchantID = merchantID;
        this.price = price;
        this.buyerName = buyerName;
        this.buyerTel = buyerTel;
        this.buyerEmail = buyerEmail;
        this.moid = moid;
        this.returnURL = returnURL;
        this.ediDate = ediDate;
        this.signData = signData;
    }

    //    String payMethod = "CARD";                      // 결제수단
//    String merchantKey = "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg=="; //상점 키
//    String merchantID = "nicepay00m";                   // 상점 id
//    String price 	  = "100"; 						    // 결제상품금액
//    String buyerName  = "테스트"; 						// 구매자명
//    String buyerTel   = "01000000000"; 				    // 구매자연락처
//    String buyerEmail = "happy@day.co.kr"; 			    // 구매자메일주소
//    String moid 	  = orderNumber; 			        // 상품주문번호
//    String returnURL  = "http://localhost:9999/callbackNicePay"; //결과페이지(절대경로)
}
