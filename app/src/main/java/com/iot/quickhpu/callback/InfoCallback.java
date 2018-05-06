package com.iot.quickhpu.callback;

import android.os.Handler;
import android.os.Message;

import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.constants.InfoConstants;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.utils.GradeJsonUtils;
import com.iot.quickhpu.utils.LogUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description
 */

public class InfoCallback implements Callback {

    private Handler mHandler;

    private MyCallback myCallback;

    public InfoCallback(Handler handler, MyCallback callback) {
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
                LogUtils.d("发送用户个人信息: " +result);
                Message msg = new Message();
                msg.what = InfoConstants.INFO_RESULT;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }
    }
}
