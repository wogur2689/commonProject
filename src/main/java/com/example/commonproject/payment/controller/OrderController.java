package com.example.commonproject.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 주문
 */
@Slf4j
@Controller
public class OrderController {
    /**
     * (nicePay)주문 페이지
     * @param model
     * @return
     */
    @GetMapping("/orderNicePay")
    public String index(Model model) {
        /*
         *****************************************
         * <결제요청 파라미터>
         * 결제시 Form 에 보내는 결제요청 필수 파라미터
         *****************************************
         */
        /* 1. 주문번호 생성 */
        int seq = 20000;
        final String orderNumber = OrderUtil.makeOrderNumber(String.valueOf(seq));

        /* 2, 데이터 생성 및 암호화 */
        Map<String, Object> payData = new HashMap<>();

        String payMethod = "CARD";                      // 결제수단
        String merchantKey = "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg=="; //상점 키
        String merchantID = "nicepay00m";                   // 상점 id
        String price 	  = "100"; 						    // 결제상품금액
        String buyerName  = "테스트"; 						// 구매자명
        String buyerTel   = "01000000000"; 				    // 구매자연락처
        String buyerEmail = "happy@day.co.kr"; 			    // 구매자메일주소
        String moid 	  = orderNumber; 			        // 상품주문번호
        String returnURL  = "http://localhost:9999/callbackNicePay"; //결과페이지(절대경로)

        /*
         * <해쉬암호화> (수정금지) -  SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법
         */
        DataEncrypt sha256Enc 	= new DataEncrypt();
        String ediDate 			= getyyyyMMddHHmmss(); //요청시간
        String signData         = sha256Enc.encrypt(ediDate + merchantID + price + merchantKey); //위변조 검증 데이터

        /* 3. 결제 요청 데이터 페이지에 내려주기*/
        model.addAttribute("payMethod", payMethod);
        model.addAttribute("merchantKey", merchantKey);
        model.addAttribute("merchantID", merchantID);
        model.addAttribute("price", price);
        model.addAttribute("buyerName", buyerName);
        model.addAttribute("buyerTel", buyerTel);
        model.addAttribute("buyerEmail", buyerEmail);
        model.addAttribute("moid", moid);
        model.addAttribute("returnURL", returnURL);
        model.addAttribute("ediDate", ediDate);
        model.addAttribute("signData", signData);

        return "index";
    }
}
