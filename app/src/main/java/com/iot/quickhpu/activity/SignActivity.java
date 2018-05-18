package com.iot.quickhpu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.ResultConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Sign;
import com.iot.quickhpu.pojo.User;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.CommonUtils;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author m1563
 * @Date 2018/5/2
 * @Description
 */

public class SignActivity extends BaseActivity {

    private Button btnCreateClass;
    private Button btnJoinClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
    }

    private void initView() {
        btnCreateClass = findViewById(R.id.btn_class_create);
        btnJoinClass = findViewById(R.id.btn_class_join);

        btnCreateClass.setOnClickListener(this);
        btnJoinClass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_class_create:// 创建课堂
                createSignLesson();
                break;
            case R.id.btn_class_join:// 加入课堂
                joinSignLesson();
                break;
        }
    }

    private void joinSignLesson() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignActivity.this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("请输入课堂邀请码");
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(SignActivity.this).inflate(R.layout.view_dialog_change_nick_name, null);
        //  设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText nickname = (EditText) view.findViewById(R.id.et_change_nick_name);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String a = nickname.getText().toString().trim();
                String userCode = getUserCode();
                String username = getUsername();
                String url = URLConstants.SIGN_MANAGER_URL + "join/" + a + ".do";
                LogUtils.d("加入课堂数据接口 " + url);
                User user = new User();
                user.setName(username);
                user.setStatus("1");
                user.setStudentId(userCode);
                String s = JSONObject.toJSONString(user);
                OkHttpUtils.postWithJsonParam(url, s, new CommonCallback(mHandler1));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void createSignLesson() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignActivity.this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("请输入课堂名称");
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(SignActivity.this).inflate(R.layout.view_dialog_change_nick_name, null);
        //  设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText nickname = (EditText) view.findViewById(R.id.et_change_nick_name);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String a = nickname.getText().toString().trim();

                String code = CommonUtils.gen4Number() + "";
                String url = URLConstants.SIGN_MANAGER_URL + "create/" + code + ".do";
                LogUtils.d("创建课堂数据接口 " + url);

                Sign sign = new Sign();
                sign.setTitle(a);
                sign.setSignId(code);
                sign.setTotal("0");
                sign.setGroup(new ArrayList<Sign.GroupBean>());

                String s = JSONObject.toJSONString(sign);
                OkHttpUtils.postWithJsonParam(url, s, new CommonCallback(mHandler));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == ResultConstants.RESULT_OK) {
                // 调到计算签到人数的页面
                String json = message.obj.toString();
                Map<String, String> resultMap = JsonUtils.responseResult(json);
                String s = resultMap.get("data");
                Sign sign = JsonUtils.jsonToPojo(s, Sign.class);
                LogUtils.d("请求创建签到接口成功 " + sign);
                ActivityUtils.toAnotherActivityWithData(SignActivity.this, StartSignActivity.class, "sign", sign);
            }
            return false;
        }
    });


    private Handler mHandler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == ResultConstants.RESULT_OK) {
                LogUtils.d("签到成功");
                ToastUtils.showLong(SignActivity.this,"签到成功");
                //ActivityUtils.toAnotherActivityWithData(SignActivity.this, StartSignActivity.class, "sign", sign);
            }
            return false;
        }
    });


}
