package com.ruki.eams.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    /*public static void main(String[] args) {
        String password = "123";//$2a$10$/W0jg6OYdr8KruAoMih.U.kI0BsnrGy8xLH3AAsYlwR7Xsmk0Gk0S
        System.out.println(encodePassword(password));
    }*/
}
