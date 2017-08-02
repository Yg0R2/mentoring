package com.epam.training.restapi.utils;

public class StringUtils {

    public static final boolean isEmpty(String s) {
        if ((s == null) || s.equals("") ) {
            return true;
        }

        return false;
    }

    public static final boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
