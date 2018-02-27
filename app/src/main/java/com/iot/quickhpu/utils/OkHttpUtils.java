package com.iot.quickhpu.utils;

import java.io.IOException;

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


    // get请求
    public static String getData(String url){
        Request request = new Request.Builder()
                .url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(">>>>>>>>>>>>>>>>> error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
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
    // post请求
    // url
    public static void getByCookie(String url){

        Request request = new Request.Builder()
                .url(url).addHeader("Cookie","PHPSESSID=eis8eumn6ncvf5qrip8kvusir5; expires=Tue, 06-Mar-2018 07:35:31 GMT; Max-Age=604800; path=/").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(">>>>>>>>>>>>>>>>> error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
                    System.out.println("success>>>>>>>>>>>>>>>  " + response.body().string());

                }
            }

        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
