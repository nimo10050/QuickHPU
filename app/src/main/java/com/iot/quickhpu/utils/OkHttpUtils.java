package com.iot.quickhpu.utils;

import com.iot.quickhpu.callback.LoginCallback;
import com.iot.quickhpu.constants.LoginConstants;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by m1563 on 2018/2/27.
 * 网络请求工具
 */

public class OkHttpUtils {

    private static OkHttpClient client = new OkHttpClient();


    // 用户名、密码 登录
    public static void login(String url, Callback callback, String... loginForm) {

        FormBody.Builder builder = new FormBody.Builder();
        FormBody form = builder.add(LoginConstants.STUDENT_ID, loginForm[0])
                .add(LoginConstants.JWC_PASSWORD, loginForm[1])
                .add(LoginConstants.XYW_PASSWORD, loginForm[2])
                .build();
        Request request = new Request.Builder()
                .url(url).post(form).build();
        //LogUtils.d("开始回调");
        client.newCall(request).enqueue(callback);
        //LogUtils.d("回调结束");
    }

    // 获取数据源
    public static void getDataByCookie(String url, Callback callback, String cookie) {

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .build();

        client.newCall(request).enqueue(callback);
    }

    // 获取数据源
    public static void getDataByCookieAndParams(String url, Callback callback, String cookie, String key, String value) {
        url += "&" + key + "=" + value;
        System.out.println(">>>>>>>>>空教室 url " + url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .build();
        client.newCall(request).enqueue(callback);
    }

    // 测试
    public static String getData1(String url) {
        Request request = new Request.Builder()
                .url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(">>>>>>>>>>>>>>>>> error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    System.out.println("success>>>>>>>>>>>>>>>  " + response.body().string());
                    System.out.println("success>>>>>>>>>>>>>>>  " + response.header("set-cookie"));

                }
            }

        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 请求
    public static void requestLocal(String url,Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }
                // post请求
                // url

    public static String getByCookie(String url) {

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", "PHPSESSID=4656as6b5c9k4mbr59m1h3r713")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(">>>>>>>>>>>>>>>>> error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.code() == 200) {

                    System.out.println("success>>>>>>>>>>>>>>>  " + response.body().string());
                }
            }

        });
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
