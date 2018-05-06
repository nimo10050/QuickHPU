package com.iot.quickhpu.callback;

import android.os.Handler;
import android.os.Message;

import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author m1563
 * @Date 2018/2/27
 * @Description 登陆的请求回调函数
 */

public class LoginCallbackTest implements Callback {



    @Override
    public void onFailure(Call call, IOException e) {
        System.out.println("响应失败:  " + e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        System.out.println("响应码: " + response.code());
        if (response.code() == 200) {
            String result = response.body().string();
            System.out.println("登录结果: " + result);
            if ("1".equals(result)){
                String cookie = response.header("set-cookie");
                System.out.println(cookie);
            }
        }
    }
}
