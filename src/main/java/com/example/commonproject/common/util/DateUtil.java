package com.example.commonproject.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    /**
     * 날짜 유효성 검사 yyyy-mm-dd
     */
    public static boolean checkDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * String To Date yyyy-MM-dd
     */
    public static Date convertStringToDate(String utilDate) throws ParseException {
        Date sf = new SimpleDateFormat("yyyy-MM-dd").parse(utilDate);
        return sf;
    }

    /**
     * Date To formatString
     */
    public static String dateToStringFormat(Date srcDate, String formatStr) {
        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        return sf.format(srcDate);
    }

    /**
     * yyyyMMddHHmmss TO LocalDateTime
     */
    public static LocalDateTime convertStringToLocalDateTime(String textDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(textDate, formatter);
    }

    /*
     * yyyy mm dd hh:mm:ss
     */
    public static String convertLocalDateTimeToString(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ldt.format(formatter);
    }
}
