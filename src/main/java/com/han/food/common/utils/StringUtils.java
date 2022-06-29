package com.han.food.common.utils;

import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static boolean matches(String str, List<String> strs) {
        if (isBlank(str)) {
            return false;
        }
        for (String pattern : strs) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    public static void main(String[] args) {
        String str = "/test";
        List<String> list = new ArrayList<>();
        list.add("/");
        list.add("/aa");
        System.out.println(matches(str, list));
    }

}
