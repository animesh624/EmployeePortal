package com.example.employeeportal.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date addSeconds(Date date, int numberOfSeconds) {
        if (date == null) date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, numberOfSeconds);
        return calendar.getTime();
    }
}
