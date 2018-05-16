package com.iot.quickhpu.callback;

import android.os.Handler;
import android.os.Message;

import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.ResultConstants;
import com.iot.quickhpu.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @作者 m1563
 * @日期 2018/5/15
 * @描述 公共回调接口
 */

public class CommonCallback implements Callback {
    private Handler mHandler;

    public CommonCallback(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        LogUtils.e("响应失败  >>>> " + e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.code() == 200) {
            String result = response.body().string();
            Message msg = new Message();
            msg.what = ResultConstants.RESULT_OK;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    }
}
