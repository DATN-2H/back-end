package com.menuplus.backend.library.util;

import java.security.SecureRandom;

public class RandomUtil {
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateCode(Integer length, Boolean isToUpperCase) {
        if (length == null || length <= 0) {
            length = 8;
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }

        String code = sb.toString();
        return Boolean.TRUE.equals(isToUpperCase) ? code.toUpperCase() : code;
    }
}

