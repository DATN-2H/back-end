package com.menuplus.backend.library.util;

public class NamingUtil {

    public static String camelCaseToSnakeCase(String input) {
        if (input == null) return null;
        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        return input.replaceAll(regex, replacement).toLowerCase();
    }
}
