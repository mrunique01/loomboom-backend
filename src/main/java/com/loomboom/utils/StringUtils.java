package com.loomboom.utils;

public class StringUtils {

    public static <T> boolean empty(T val) {
        if (val == null) {
            return true;
        }

        if (val instanceof String) {
            return ((String) val).trim().isEmpty();
        }

        if(val instanceof Boolean){
            return !((Boolean)val);
        }

        if (val.getClass().isArray()) {
            return ((Object[]) val).length == 0;
        }
        // Handle other types or return false if not a string
        return false;
    }

}
