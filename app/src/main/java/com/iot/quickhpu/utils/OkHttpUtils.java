package com.iot.quickhpu.utils;

import com.iot.quickhpu.constants.LoginConstants;

import java.io.IOException;
import java.text.Normalizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @作者 LOZON
 * @日期 2018/5/15
 * @描述 网络请求工具类
 */
public class OkHttpUtils {

    private static OkHttpClient client = new OkHttpClient();

    /**
     * 登录表单请求
     * @param url
     * @param callback
     * @param loginForm
     */
    public static void login(String url, Callback callback, String... loginForm) {

        FormBody.Builder builder = new FormBody.Builder();
        FormBody form = builder.add(LoginConstants.STUDENT_ID, loginForm[0])
                .add(LoginConstants.JWC_PASSWORD, loginForm[1])
                .add(LoginConstants.XYW_PASSWORD, loginForm[2])
                .build();
        Request request = new Request.Builder()
                .url(url).post(form).build();
        client.newCall(request).enqueue(callback);
    }


    public static void postWithForm(String url,String cookie, Callback callback, String... forms) {

        FormBody.Builder builder = new FormBody.Builder();
        FormBody form = builder.add("name", forms[0])
                .add("page", forms[1])
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(form)
                .addHeader("Cookie", cookie)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * GET 请求 远程服务器
     * @param url
     * @param callback
     * @param cookie
     */
    public static void getDataByCookie(String url, Callback callback, String cookie) {

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .build();

        client.newCall(request).enqueue(callback);
    }

    /**
     * GET 请求  携带参数
     * @param url 请求接口
     * @param callback 回调接口
     * @param cookie cookie
     * @param key 请求参数的键
     * @param value 请求参数的值
     */
    public static void getDataByCookieAndParams(String url, Callback callback
            , String cookie, String key, String value) {
        url += "&" + key + "=" + value;
        System.out.println(">>>>>>>>>空教室 url " + url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * GET请求
     * @param url 请求接口
     * @param callback 回调接口
     */
    public static void getWithoutParam(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }


    /**
     * post请求
     * 请求体为Json字符串
     * @param url 请求接口
     * @param json 请求参数
     * @param callback 回调接口
     */
    public static void postWithJsonParam(String url, String json, Callback callback) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }


    /**
     * 测试
     * @param url
     * @return
     */
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
