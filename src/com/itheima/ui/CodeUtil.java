package com.itheima.ui;

import java.util.Random;

public class CodeUtil {


    public static String getCode() {
        String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for(int i=0;i<4;i++){
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }
}
