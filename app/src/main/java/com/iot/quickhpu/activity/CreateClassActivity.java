package com.iot.quickhpu.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.NewClassCallback;
import com.iot.quickhpu.constants.SpConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import java.net.URL;
import java.util.Map;

public class CreateClassActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etClassName;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        initView();
    }

    private void initView() {
        etClassName = findViewById(R.id.et_new_class_name);
        btnCreate = findViewById(R.id.btn_new_class);
        btnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_new_class) {
            String s = etClassName.getText().toString();
            String code = (String) SpUtils.get(this, SpConstants.USER_CODE,"1234");
            String url = URLConstants.CLASS_MANAGER_URL + "new/" + code + ".do?title=" + s;
            LogUtils.d(">>>>>>>>请求地址   " + url);
            OkHttpUtils.requestLocal(url, new NewClassCallback(mHandler));
        }
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    LogUtils.d(">>>>>>>>   " + (String) message.obj);
                    Map<String, String> result = JsonUtils.responseResult((String) message.obj);
                    if ("500".equals(result.get("status"))){
                        ToastUtils.showLong(CreateClassActivity.this,result.get("msg"));
                        return false;
                    }
                    ActivityUtils.toAnotherActivity(CreateClassActivity.this
                            ,ClassManagerActivity.class);
                    finish();
                    break;
            }
            return false;
        }
    });
}
