package com.banzhuan.repairservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    public static int getMonth(Integer time){
        String formats = "MM";
        Long timestamp = Long.parseLong(time.toString()) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return Integer.valueOf(date);
    }
}
