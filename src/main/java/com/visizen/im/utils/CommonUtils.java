package com.visizen.im.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static String formatDate(Date date,String pattern){
        if(date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
