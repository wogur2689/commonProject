package com.example.commonproject.payment.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NicePayDto {
    /*
     ****************************************************************************************
     * <인증 결과 파라미터>
     ****************************************************************************************
     */
    private String authResultCode; 	// 인증결과 : 0000(성공)
    private String authResultMsg; 	// 인증결과 메시지
    private String nextAppURL; 		// 승인 요청 URL
    private String txTid; 			// 거래 ID
    private String authToken; 		// 인증 TOKEN
    private String payMethod; 		// 결제수단
    private String mid; 			// 상점 아이디
    private String moid; 			// 상점 주문번호
    private String amt; 			// 결제 금액
    private String reqReserved; 	// 상점 예약필드
    private String netCancelURL; 	// 망취소 요청 URL
    private String authSignature;   // Nicepay에서 내려준 응답값의 무결성 검증 Data
}
