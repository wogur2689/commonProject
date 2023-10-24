package com.example.commonproject.payment.controller;

import com.example.commonproject.common.properties.PropertiesValue;
import com.example.commonproject.payment.util.OrderUtil;
import com.example.commonproject.payment.util.PaymentUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * 결제(nicePay)
 */
@Slf4j
@Controller
@RequestMapping("/payment")
public class PaymentController {
    /**
     * (nicePay)결제 콜백
     */
    @PostMapping("/callBackNicePay")
    public String callBackNicePay(HttpServletRequest request, Model model) throws Exception {
        //1. 인증결과 인코딩
        request.setCharacterEncoding("utf-8");

        //2. 인증결과 파라미터 추출
        String code 	        = request.getParameter("AuthResultCode"); 	// 인증결과 : 0000(성공)
        String msg 	            = request.getParameter("AuthResultMsg"); 	// 인증결과 메시지
        String nextAppURL 		= request.getParameter("NextAppURL"); 		// 승인 요청 URL
        String txTid 			= request.getParameter("TxTid"); 			// 거래 ID
        String authToken 		= request.getParameter("AuthToken"); 		// 인증 TOKEN
        String payMethod 		= request.getParameter("PayMethod"); 		// 결제수단
        String mid 				= request.getParameter("MID"); 				// 상점 아이디
        String moid 			= request.getParameter("Moid"); 			    // 상점 주문번호
        String amt 				= request.getParameter("Amt"); 				// 결제 금액
        String reqReserved 		= request.getParameter("ReqReserved"); 		// 상점 예약필드
        String netCancelURL 	= request.getParameter("NetCancelURL"); 	    // 망취소 요청 URL
        String signature        = request.getParameter("Signature");	        // Nicepay에서 내려준 응답값의 무결성 검증 Data
        //TODO db저장 추가
        log.info("### AuthResultCode {}, AuthResultMsg {} ###", code, msg);
        /*
         ****************************************************************************************
         * Signature : 요청 데이터에 대한 무결성 검증을 위해 전달하는 파라미터로 허위 결제 요청 등 결제 및 보안 관련 이슈가 발생할 만한 요소를 방지하기 위해 연동 시 사용하시기 바라며
         * 위변조 검증 미사용으로 인해 발생하는 이슈는 당사의 책임이 없음 참고하시기 바랍니다.
         ****************************************************************************************
         */

        // 인증 응답 Signature = hex(sha256(AuthToken + MID + Amt + MerchantKey)
        // String authComparisonSignature = sha256Enc.encrypt(authToken + mid + amt + merchantKey);


        /*
         ****************************************************************************************
         * <승인 결과 파라미터 정의>
         * 샘플페이지에서는 승인 결과 파라미터 중 일부만 예시되어 있으며,
         * 추가적으로 사용하실 파라미터는 연동메뉴얼을 참고하세요.
         ****************************************************************************************
         */
        String ResultCode 	= ""; String ResultMsg 	= ""; String PayMethod 	= "";
        String GoodsName 	= ""; String Amt 		= ""; String TID 		= "";
        // String Signature = ""; String paySignature = "";

        /* <인증 결과 성공시 승인 진행> */
        String resultJsonStr = "";
        if(code.equals("0000")){

            /* <해쉬암호화> SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다.*/
            String merchantKey 		= PropertiesValue.nicePayMerchantKey; // 상점키
            String ediDate			= OrderUtil.getyyyyMMddHHmmss();
            String signData 		= PaymentUtil.encrypt(authToken + mid + amt + ediDate + merchantKey);

            /* <승인 요청>승인에 필요한 데이터 생성 후 server to server 통신을 통해 승인 처리 합니다. */
            StringBuffer requestData = new StringBuffer();
            requestData.append("TID=").append(txTid).append("&");
            requestData.append("AuthToken=").append(authToken).append("&");
            requestData.append("MID=").append(mid).append("&");
            requestData.append("Amt=").append(amt).append("&");
            requestData.append("EdiDate=").append(ediDate).append("&");
            requestData.append("CharSet=").append("utf-8").append("&");
            requestData.append("SignData=").append(signData);

            resultJsonStr = PaymentUtil.connectToServer(requestData.toString(), nextAppURL);

            HashMap resultData = new HashMap();
            boolean paySuccess = false;
            if("9999".equals(resultJsonStr)){
                /* <망취소 요청> 승인 통신중에 Exception 발생시 망취소 처리를 권고합니다.*/
                StringBuffer netCancelData = new StringBuffer();
                requestData.append("&").append("NetCancel=").append("1");
                String cancelResultJsonStr = PaymentUtil.connectToServer(requestData.toString(), netCancelURL);

                HashMap cancelResultData = PaymentUtil.jsonStringToHashMap(cancelResultJsonStr);
                ResultCode = (String)cancelResultData.get("ResultCode");
                ResultMsg = (String)cancelResultData.get("ResultMsg");
                /*Signature = (String)cancelResultData.get("Signature");
                String CancelAmt = (String)cancelResultData.get("CancelAmt");
                paySignature = sha256Enc.encrypt(TID + mid + CancelAmt + merchantKey);*/
            }else{
                resultData = PaymentUtil.jsonStringToHashMap(resultJsonStr);
                ResultCode 	= (String)resultData.get("ResultCode");	// 결과코드 (정상 결과코드:3001)
                ResultMsg 	= (String)resultData.get("ResultMsg");	// 결과메시지
                PayMethod 	= (String)resultData.get("PayMethod");	// 결제수단
                GoodsName   = (String)resultData.get("GoodsName");	// 상품명
                Amt       	= (String)resultData.get("Amt");		// 결제 금액
                TID       	= (String)resultData.get("TID");		// 거래번호
                // Signature : Nicepay에서 내려준 응답값의 무결성 검증 Data
                // 가맹점에서 무결성을 검증하는 로직을 구현하여야 합니다.
                /*Signature = (String)resultData.get("Signature");
                paySignature = sha256Enc.encrypt(TID + mid + Amt + merchantKey);*/

                /* <결제 성공 여부 확인> */
                if(PayMethod != null){
                    if(PayMethod.equals("CARD")){
                        if(ResultCode.equals("3001")) paySuccess = true; // 신용카드(정상 결과코드:3001)
                    }else if(PayMethod.equals("BANK")){
                        if(ResultCode.equals("4000")) paySuccess = true; // 계좌이체(정상 결과코드:4000)
                    }else if(PayMethod.equals("CELLPHONE")){
                        if(ResultCode.equals("A000")) paySuccess = true; // 휴대폰(정상 결과코드:A000)
                    }else if(PayMethod.equals("VBANK")){
                        if(ResultCode.equals("4100")) paySuccess = true; // 가상계좌(정상 결과코드:4100)
                    }else if(PayMethod.equals("SSG_BANK")){
                        if(ResultCode.equals("0000")) paySuccess = true; // SSG은행계좌(정상 결과코드:0000)
                    }else if(PayMethod.equals("CMS_BANK")){
                        if(ResultCode.equals("0000")) paySuccess = true; // 계좌간편결제(정상 결과코드:0000)
                    }
                }
                log.info("### paySuccess {} ###", paySuccess);
            }
        }else/*if(authSignature.equals(authComparisonSignature))*/{
            ResultCode 	= code;
            ResultMsg 	= msg;
        }/*else{
	System.out.println("인증 응답 Signature : " + authSignature);
	System.out.println("인증 생성 Signature : " + authComparisonSignature);
    */
        log.info("### 결제결과 {} ###", ResultMsg);
        model.addAttribute("code", ResultCode);
        model.addAttribute("ResultMsg", ResultMsg);
        model.addAttribute("PayMethod", payMethod);
        model.addAttribute("Amt", Amt);
        model.addAttribute("TID", TID);
        return "payment/nicepay/payResultPage";
    }

    /**
     * (nicePay)결제취소
     */
    @PostMapping("/cancelCallBackNicePay")
    public String cancelCallBackNicePay(HttpServletRequest request, Model model) throws Exception {
        request.setCharacterEncoding("utf-8");
        /* <취소요청 파라미터> */
        String tid = (String) request.getParameter("TID");    // 거래 ID
        String cancelAmt = (String) request.getParameter("CancelAmt");    // 취소금액
        String partialCancelCode = (String) request.getParameter("PartialCancelCode");    // 부분취소여부
        String mid = "nicepay00m";    // 상점 ID
        String moid = "nicepay_api_3.0_test";    // 주문번호
        String cancelMsg = "고객요청";    // 취소사유

        /* <해쉬암호화> SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다. */
        String merchantKey = PropertiesValue.nicePayMerchantKey; // 상점키
        String ediDate = OrderUtil.getyyyyMMddHHmmss();
        String signData = PaymentUtil.encrypt(mid + cancelAmt + ediDate + merchantKey);

        /* <취소 요청> 취소에 필요한 데이터 생성 후 server to server 통신을 통해 취소 처리 합니다.
         취소 사유(CancelMsg) 와 같이 한글 텍스트가 필요한 파라미터는 euc-kr encoding 처리가 필요합니다. */
        StringBuffer requestData = new StringBuffer();
        requestData.append("TID=").append(tid).append("&");
        requestData.append("MID=").append(mid).append("&");
        requestData.append("Moid=").append(moid).append("&");
        requestData.append("CancelAmt=").append(cancelAmt).append("&");
        requestData.append("CancelMsg=").append(URLEncoder.encode(cancelMsg, "euc-kr")).append("&");
        requestData.append("PartialCancelCode=").append(partialCancelCode).append("&");
        requestData.append("EdiDate=").append(ediDate).append("&");
        requestData.append("CharSet=").append("utf-8").append("&");
        requestData.append("SignData=").append(signData);
        String resultJsonStr = PaymentUtil.connectToServer(requestData.toString(), "https://webapi.nicepay.co.kr/webapi/cancel_process.jsp");

        /* <취소 결과 파라미터 정의> */
        String ResultCode 	= ""; String ResultMsg 	= ""; String CancelAmt 	= "";
        String CancelDate 	= ""; String CancelTime = ""; String TID 		= ""; String Signature = "";

        /* Signature : 요청 데이터에 대한 무결성 검증을 위해 전달하는 파라미터로 허위 결제 요청 등 결제 및 보안 관련 이슈가 발생할 만한 요소를 방지하기 위해 연동 시 사용하시기 바라며
         * 위변조 검증 미사용으로 인해 발생하는 이슈는 당사의 책임이 없음 참고하시기 바랍니다.
         */
//String Signature = ""; String cancelSignature = "";

        if("9999".equals(resultJsonStr)){
            ResultCode 	= "9999";
            ResultMsg	= "통신실패";
        }else{
            HashMap resultData = PaymentUtil.jsonStringToHashMap(resultJsonStr);
            ResultCode 	= (String)resultData.get("ResultCode");	// 결과코드 (취소성공: 2001, 취소성공(LGU 계좌이체):2211)
            ResultMsg 	= (String)resultData.get("ResultMsg");	// 결과메시지
            CancelAmt 	= (String)resultData.get("CancelAmt");	// 취소금액
            CancelDate 	= (String)resultData.get("CancelDate");	// 취소일
            CancelTime 	= (String)resultData.get("CancelTime");	// 취소시간
            TID 		= (String)resultData.get("TID");		// 거래아이디 TID
            //Signature       	= (String)resultData.get("Signature");
            //cancelSignature = sha256Enc.encrypt(TID + mid + CancelAmt + merchantKey);
        }
        log.info("### 취소결과 {} ###", ResultMsg);
        model.addAttribute("ResultMsg", ResultMsg);
        model.addAttribute("CancelAmt", CancelAmt);
        model.addAttribute("CancelDate", CancelDate);
        model.addAttribute("CancelTime", CancelTime);

        return "payment/nicepay/cancelPage";
    }
}
