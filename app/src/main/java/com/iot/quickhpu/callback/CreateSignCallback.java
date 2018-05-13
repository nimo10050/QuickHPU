package com.iot.quickhpu.callback;

import android.os.Handler;
import android.os.Message;

import com.iot.quickhpu.constants.InfoConstants;
import com.iot.quickhpu.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description
 */

public class CreateSignCallback implements Callback {

    private Handler mHandler;

    private MyCallback myCallback;

    public CreateSignCallback(Handler handler, MyCallback callback) {
        mHandler = handler;
        //myCallback = callback;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.code() == 200) {
            String result = response.body().string();
            if (result != null && !"".equals(result)) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }
    }
}
