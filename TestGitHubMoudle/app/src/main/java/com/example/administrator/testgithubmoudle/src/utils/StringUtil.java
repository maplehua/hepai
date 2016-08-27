package com.example.administrator.testgithubmoudle.src.utils;

/**
 * Created by Administrator on 2016/8/25.
 */
public class StringUtil {

    public static boolean isEmptyOrNull(String str) {
        boolean res = false;
        if (str == null || str.equals("")) {
            res = true;
        }
        return res;
    }
}
