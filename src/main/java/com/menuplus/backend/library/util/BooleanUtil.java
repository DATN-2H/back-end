package com.menuplus.backend.library.util;

public class BooleanUtil {

    public static boolean isTrue(Boolean value) {
        return value != null && value;
    }

    public static boolean isFalse(Boolean value) {
        return value == null || !value;
    }
}
