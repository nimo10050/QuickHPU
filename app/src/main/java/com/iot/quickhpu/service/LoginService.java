package com.iot.quickhpu.service;

import android.content.Context;
import android.os.Handler;

import com.iot.quickhpu.callback.LoginCallback;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.ToastUtils;

/**
 * @Author m1563
 * @Date 2018/2/28
 * @Description 登录业务类
 */

public class LoginService {

    private Handler mHandler;

    public LoginService(Handler handler) {
        mHandler = handler;
    }

    // 登录校验
    public boolean validateForm(Context context, String studentId, String jwcPwd, String xywPwd) {
        if (studentId == null || studentId.length() == 0) {
            ToastUtils.showShort(context, "学号不能为空");
            return false;
        } else if (jwcPwd == null || jwcPwd.length() == 0) {
            ToastUtils.showShort(context, "教务处密码不能为空");
            return false;
        } else if (xywPwd == null || xywPwd.length() == 0) {
            ToastUtils.showShort(context, "校园网不能为空");
            return false;
        }
        return true;
    }

    // 登陆业务
    public void login(String studentId, String jwcPwd, String xywPwd) {
        OkHttpUtils.login(URLConstants.LOGIN_URL,new LoginCallback(mHandler),studentId,jwcPwd,xywPwd);
    }

}
