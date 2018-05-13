package com.iot.quickhpu.activity;

import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.RSACryptographyCallback;
import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.SpConstants;
import com.iot.quickhpu.service.LoginService;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.RSACryptographyUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // 表单输入控件
    private EditText etLoginStuId;
    private EditText etLoginJwcPwd;
    private EditText etLoginXywPwd;

    // 登录按钮
    private Button btnLogin;

    // 加载弹框
    AlertDialog loadingDialog;


    // 用户登录信息
    String studentId;
    String jwcPwd;
    String xywPwd;

    // 登录业务对象
    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inspectLogin();
    }

    /**
     * 判断是否已经登陆
     * 返回值 1 已登录
     */
    private void inspectLogin() {
        int loginResult = (int) SpUtils.get(this, SpConstants.IS_LOGIN, 0);
        if (loginResult == 1) {
            ActivityUtils.toAnotherActivity(this, MainActivity.class);
            finish();
        } else {
            initView();
            initEvent();
        }
    }

    // 初始化监听器
    private void initEvent() {
        btnLogin.setOnClickListener(this);
        loginService = new LoginService(handler);
    }

    // 网络请求相应结果
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case LoginConstants.LOGIN_SUCCESS:
                    ActivityUtils.toAnotherActivity(LoginActivity.this,MainActivity.class);
                    // 保存用户信息
                    SpUtils.put(LoginActivity.this,SpConstants.IS_LOGIN,1);
                    SpUtils.put(LoginActivity.this,SpConstants.USER_CODE,studentId);
                    SpUtils.put(LoginActivity.this,LoginConstants.LOGIN_COOKIE,message.obj);
                    // 销毁登录页面
                    finish();
                    break;
            }
            return false;
        }
    });

    // 初始化控件
    private void initView() {
        etLoginStuId = findViewById(R.id.et_login_studentId);
        etLoginJwcPwd = findViewById(R.id.et_login_jwc_pwd);
        etLoginXywPwd = findViewById(R.id.et_login_xyw_pwd);

        btnLogin = findViewById(R.id.btn_login);

        loadingDialog = new SpotsDialog(this);
    }


    // 登录触发
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loadingDialog.show();
                login();
                break;
        }
    }

    // 登录
    private void login() {
        // 校验表单输入
        boolean noError = validateForm();
        if (noError) {
            xywPwd = RSACryptographyUtils.encrypt(new WebView(this),new RSACryptographyCallback(){
                @Override
                public void onSuccess(String result) {
                    loginService.login(studentId,jwcPwd,result);
                }
            },xywPwd);
        }
    }

    // 校验表单
    public boolean validateForm() {
        studentId = etLoginStuId.getText().toString().trim();
        jwcPwd = etLoginJwcPwd.getText().toString().trim();
        xywPwd = etLoginXywPwd.getText().toString().trim();
        return loginService.validateForm(this, studentId, jwcPwd, xywPwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog!=null) {
            loadingDialog.dismiss();
        }
    }
}
