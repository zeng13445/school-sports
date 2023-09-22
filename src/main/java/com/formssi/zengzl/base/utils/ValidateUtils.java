package com.formssi.zengzl.base.utils;

import java.util.Collection;
import java.util.Map;

public class ValidateUtils {
    /**
     * whitespace characters
     */
    public static final String whitespace = " \t\n\r";

    /**
     * Check whether an object is empty, will see if it is a String, Map, Collection, etc.
     */
    public static boolean isEmpty(Object o) {
        return isValidEmpty(o);
    }
    public static boolean isNotEmpty(Object o) {
        return !isValidEmpty(o);
    }

    public static boolean isEmpty(String s) {
        return (s == null) || s.length() == 0;
    }

    /**
     * Returns true if string s is empty or whitespace characters only.
     */
    public static boolean isWhitespace(String s) {
        // Is s empty?
        if (isEmpty(s)) return true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (whitespace.indexOf(c) == -1) return false;
        }
        return true;
    }

    private static boolean isValidEmpty(Object value) {
        if (value == null) return true;
        if (value instanceof String) return ((String) value).length() == 0;
        if (value instanceof Collection) return ((Collection<? extends Object>) value).size() == 0;
        if (value instanceof Map) return ((Map<? extends Object, ? extends Object>) value).size() == 0;
        if (value instanceof CharSequence) return ((CharSequence) value).length() == 0;
        if (value instanceof IsEmpty) return ((IsEmpty) value).isEmpty();
        if (value instanceof Boolean) return false;
        if (value instanceof Number) return false;
        if (value instanceof Character) return false;
        if (value instanceof java.util.Date) return false;

        return false;
    }

}