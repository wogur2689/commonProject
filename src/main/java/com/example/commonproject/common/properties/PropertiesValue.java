package com.example.commonproject.common.properties;

public class PropertiesValue {
    public static final String nicePayMerchantKey = Properties.getProperty("nicePay.merchantKey");
    public static final String nicePayMerchantID = Properties.getProperty("nicePay.merchantID");
    public static final String nicePayReturnURL = Properties.getProperty("nicePay.returnURL");
}
