package com.example.demo.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成cookie公共类
 *
 * @author: yingmuhuadao
 * @date: Created in 14:19 2018/9/7
 */
public class CreateCookieUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getCookie(){

        return sdf.format(new Date())+""+rd(100000, 999999)+""+rd(100000, 999999)+""+rd(100000, 999999)+guid().substring(0, 16) + "" + guid().substring(0, 16);
    }


    public static BigDecimal rd(int m, int n){
        int c = m - n + 1;
        return BigDecimal.valueOf(Double.valueOf(Math.floor(Math.random() * c + n)).longValue(),0);
    }


    public static String guid(){
        return (S4() + S4() + "" + S4() + "" + S4() + "" + S4() + "" + S4() + S4() + S4()+S4()).substring(0,16);
    }


    public static String S4(){
        String s = Integer.toHexString((Double.valueOf(((1 + Math.random()) * 0x10000)).intValue() | 0));
        return s.substring(1,s.length());
    }


    public static void main(String[] args) {
        System.out.println("201809190931301751421559547989647c16293542c629b342b69c5fb96b23fe".hashCode());
    }
}
