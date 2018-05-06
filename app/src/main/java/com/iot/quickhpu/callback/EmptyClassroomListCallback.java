package com.iot.quickhpu.callback;

import android.os.Handler;
import android.os.Message;

import com.iot.quickhpu.constants.EmptyClassroomConstants;
import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.pojo.EmptyClassroom;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.utils.EmptyClassroomJsonUtils;
import com.iot.quickhpu.utils.LogUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 空教室回调接口
 */

public class EmptyClassroomListCallback implements Callback {

    private Handler mHandler;


    public EmptyClassroomListCallback(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        LogUtils.d(">>>>>>>>>>响应失败 " + e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.code() == 200) {
            String result = response.body().string();
            if (result != null && !"".equals(result)) {
                LogUtils.d("空教室数据 : " + result);
                List<EmptyClassroom> emptyClassroomList = EmptyClassroomJsonUtils.jsonToList(result);
                Message msg = new Message();
                msg.what = EmptyClassroomConstants.EMPTY_CLASSROOM_RESULT;
                msg.obj = emptyClassroomList;
                mHandler.sendMessage(msg);
            }
        }
    }
}
