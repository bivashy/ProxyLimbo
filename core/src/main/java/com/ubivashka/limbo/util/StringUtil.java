package com.ubivashka.limbo.util;

public class StringUtil {
    private StringUtil(){}

    public static String toSnakeCase(String text){
        return text.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
