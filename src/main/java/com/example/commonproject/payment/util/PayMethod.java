package com.example.commonproject.payment.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayMethod {
    CARD("3001"),
    BANK("4000"),
    CELLPHONE("A000"),
    VBANK("4100"),
    SSG_BANK("0000"),
    CMS_BANK("0000");

    private final String resultCode;

    public static PayMethod fromString(String cardString) {
        switch (cardString.toUpperCase()) {
            case "CARD" -> {
                return CARD;
            }
            case "BANK" -> {
                return BANK;
            }
            case "CELLPHONE" -> {
                return CELLPHONE;
            }
            case "VBANK" -> {
                return VBANK;
            }
            case "SSG_BANK" -> {
                return SSG_BANK;
            }
            case "CMS_BANK" -> {
                return CMS_BANK;
            }
            default -> {
                return null;
            }
        }
    }
}
