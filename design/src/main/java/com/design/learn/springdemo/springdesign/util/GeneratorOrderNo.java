package com.design.learn.springdemo.springdesign.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GeneratorOrderNo {

    private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("YYYYMMddHHmmssSSS");

   public static String getRequestNo(String prefix){
       String dateTime = "";
       synchronized (GeneratorOrderNo.class){
           dateTime = datetimeFormat.format(getNow());
       }

       return prefix+dateTime+generateNumber(100);

   }

   public static Date getNow(){
       Calendar cal = Calendar.getInstance();
       Date currDate = cal.getTime();
       return currDate;
   }

    public static int generateNumber(int bound) {
        int number = (int) (Math.random() * bound);
        return number;
    }

}
