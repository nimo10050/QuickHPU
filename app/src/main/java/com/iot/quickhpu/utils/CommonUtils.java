package com.iot.quickhpu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author m1563
 * @Date 2018/5/16
 * @Description 公共工具类
 */

public class CommonUtils {

    /**
     * 获得当前时间
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String d = date.format(new Date());
        return d;
    }

    /**
     * id 生成器
     */
    public static long genId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }
}
