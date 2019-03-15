package com.example.demo1.util;

import org.springframework.util.DigestUtils;

import java.util.Random;

public class MyMD5Util {

    public static String md5(String password) {
        String salt = random(5); //生成几位的验证码
        String newPassword = password + salt;
        return DigestUtils.md5DigestAsHex(newPassword.getBytes());
    }
    //salt 生成
    private static String random(int mu){
        Random random = new Random();
        String chars = "qazwsxedcrfvtgbyhnujmikolp";
        String returns = "";
        for(int i = 0 ; i < mu ;i ++ ){
            returns += chars.charAt(random.nextInt(chars.length()));
        }
        return returns;
    }
}
