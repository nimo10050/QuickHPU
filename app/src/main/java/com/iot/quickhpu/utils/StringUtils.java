package com.iot.quickhpu.utils;

/**
 * @Author m1563
 * @Date 2018/5/6
 * @Description String 工具类
 */

public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !"".equals(s.trim());
    }

}
