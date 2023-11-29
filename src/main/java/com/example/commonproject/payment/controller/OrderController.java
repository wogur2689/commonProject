package com.example.commonproject.payment.controller;

import com.example.commonproject.common.properties.PropertiesValue;
import com.example.commonproject.payment.dto.OrderDto;
import com.example.commonproject.payment.util.OrderUtil;
import com.example.commonproject.payment.util.PaymentUtil;
import com.example.commonproject.payment.vo.OrderRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 주문
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    /**
     * (nicePay)주문 페이지
     * @param model
     * @return
     */
    @GetMapping("/nicepay/orderPage")
    public String orderNicePay(OrderRequestVO orderRequestVO, Model model) {
        // 1. 임시 주문번호 생성
        int seq = 20000;
        final String orderNumber = OrderUtil.makeOrderNumber(String.valueOf(seq));

        // 2. <해쉬암호화> (수정금지) -  SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법
        String ediDate = OrderUtil.getyyyyMMddHHmmss(); //요청시간
        String signData = PaymentUtil.encrypt(ediDate + PropertiesValue.nicePayMerchantID + orderRequestVO.getPrice() + PropertiesValue.nicePayMerchantKey); //위변조 검증 데이터

        // 3. 데이터 주문 페이지에 내려주기
        OrderDto orderDto = OrderDto.builder()
                .payMethod(orderRequestVO.getPayMethod())
                .merchantKey(PropertiesValue.nicePayMerchantKey)
                .merchantID(PropertiesValue.nicePayMerchantID)
                .price(orderRequestVO.getPrice())
                .buyerName(orderRequestVO.getBuyerName())
                .buyerEmail(orderRequestVO.getBuyerEmail())
                .buyerTel(orderRequestVO.getBuyerTel())
                .moid(orderNumber)
                .returnURL(PropertiesValue.nicePayReturnURL)
                .ediDate(ediDate)
                .signData(signData)
                .build();

        //TODO db 저장 로직 추가
        model.addAttribute("payData", orderDto);

        return "payment/nicepay/orderPage";
    }
}
