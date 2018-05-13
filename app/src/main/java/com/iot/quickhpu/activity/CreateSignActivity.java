package com.iot.quickhpu.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.CreateSignCallback;
import com.iot.quickhpu.callback.MyCallback;
import com.iot.quickhpu.utils.OkHttpUtils;

import org.w3c.dom.Text;

public class CreateSignActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvCode;
    private RecyclerView rvSignResult;
    private Button btnEndSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sign);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnEndSign.setOnClickListener(this);
    }

    private void initView() {
        tvCode = findViewById(R.id.tv_code);
        // 四位随机数
        int code = (int)((Math.random()*9+1)*1000);
        tvCode.setText(code);
        // 发送网络请求
        String url = "http://192.168.31.216/sign/create/"+code;
        OkHttpUtils.requestLocal(url,new CreateSignCallback(mHandler, null));
        rvSignResult = findViewById(R.id.rv_result_sign);
        btnEndSign =findViewById(R.id.btn_sign_end);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_sign_end){
            // 结束签到
        }
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            return false;
        }
    });
}
