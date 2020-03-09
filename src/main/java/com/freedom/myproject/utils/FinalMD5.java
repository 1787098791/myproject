package com.freedom.myproject.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class FinalMD5 {
    public static String getFinalMD5(String str){
        String md5 = getMD5(str);
        md5 = getMD5(md5);
        md5 = getMD5(md5.substring(2,16));
        md5 = getMD5(md5.substring(8,24));
        return md5;
    }
    public static String getMD5(String value){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] digest =md5.digest(value.getBytes());
            String s = new BigInteger(1,digest).toString(16);
            // 长度不够时前面补0
            for (int i = 0; i < 32 - s.length() ;i++) {
                s = "0" + s;
            }

            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
