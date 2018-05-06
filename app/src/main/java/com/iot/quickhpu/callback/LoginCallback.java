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

public class LoginCallback implements Callback {

    private Handler mHandler;

    public LoginCallback(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onFailure(Call call, IOException e) {
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.code() == 200) {
            String result = response.body().string();
            if ("1".equals(result)){
                String cookie = response.header("set-cookie");
                Message msg = new Message();
                msg.what = LoginConstants.LOGIN_SUCCESS;
                msg.obj = cookie;
                mHandler.sendMessage(msg);
            }
        }
    }
}
