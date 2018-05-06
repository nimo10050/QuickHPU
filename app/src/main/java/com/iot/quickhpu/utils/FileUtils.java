package com.iot.quickhpu.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.iot.quickhpu.activity.StudentDetailActivity;
import com.iot.quickhpu.pojo.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author m1563
 * @Date 2018/5/1
 * @Description 文件操作
 */

public class FileUtils {

    /**
     * 读取文件
     *
     * @param filePath
     * @return 字符串
     */
    public static String readJsonFile(Context context, String filePath) {

        LogUtils.d("开始读取文件 >>>>>>>  " + filePath);
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(filePath);
            byte[] buff = new byte[1024];
            int len = 0;
            int b = 0;
            String result = null;
            while ((b = is.read()) != -1) {
                buff[len] = (byte) b;
                len++;
            }
            result = new String(buff, 0, len);
            LogUtils.d("json >>>>>>>>>>>>> " + result);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void writeJsonFile(Context context, String s,String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(s);
            os.write(data.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
