/**
 * @author 曾俞钧
 * @date 2020/3/23 16:08
 * @version 1.0
 */
package com.example.springboot_son.utils;
//时间搓验证工具类
public class TokenVerification {
    public  boolean  verification(String user_token){

        if (isEmpty(user_token)){
            return true;
        }
        // 一个月时间 2592000
        String sub=user_token.substring(user_token.indexOf("-")+1);
        long l = Long.parseLong( sub )/1000;
         long i= System.currentTimeMillis()/1000;
        if (i-l<2592000){
            return true;
        }

       return  false;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || "null".equals(str.trim());
    }

}
