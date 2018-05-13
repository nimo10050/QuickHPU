package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.iot.quickhpu.R;
import com.iot.quickhpu.utils.ActivityUtils;

/**
 * @Author m1563
 * @Date 2018/5/2
 * @Description
 */

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMyClass;
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
        btnMyClass = findViewById(R.id.btn_class_myself);
        btnJoinClass = findViewById(R.id.btn_class_join);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_class_myself:// 我的课堂

                break;
            case R.id.btn_class_create:// 创建课堂
                ActivityUtils.toAnotherActivity(this,CreateSignActivity.class);
                break;
            case R.id.btn_class_join:// 加入课堂

                break;
        }
    }
}
