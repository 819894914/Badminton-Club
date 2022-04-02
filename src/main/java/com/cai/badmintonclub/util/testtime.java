package com.cai.badmintonclub.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testtime {
    public static void testtime(){
        Date date=new Date();
        System.out.println(date);
        SimpleDateFormat ft=new SimpleDateFormat ("yyyy-MM-dd");
        System.out.println(ft.format(date));
    }

    public static void main(String[] args) {
        testtime();
    }
}
