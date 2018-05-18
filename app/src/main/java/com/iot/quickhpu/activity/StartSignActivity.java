package com.iot.quickhpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.SignResultAdapter;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.ResultConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Sign;
import com.iot.quickhpu.utils.HPUResult;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StartSignActivity extends BaseActivity {

    @BindView(R.id.tv_sign_name)
    TextView tvSignName;
    @BindView(R.id.tv_sign_code)
    TextView tvSignCode;
    @BindView(R.id.btn_sign)
    Button btnSign;
    @BindView(R.id.lv_sign_student)
    ListView lvSignStudent;
    @BindView(R.id.tv_sign_total)
    TextView tvSignTotal;

    private Unbinder unbinder;


    Sign sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_sign);
        unbinder = ButterKnife.bind(this);

        Intent intent = getIntent();
        sign = (Sign) intent.getSerializableExtra("sign");
        tvSignCode.setText(" 签到码：" + sign.getSignId());
        tvSignName.setText(" 课堂名称 ：" + sign.getTitle());
        tvSignTotal.setText(" 签到人数 ：" + sign.getTotal());

        btnSign.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        String userCode = getUserCode();
        String url = URLConstants.SIGN_MANAGER_URL + "over/" + sign.getSignId() + ".do";
        LogUtils.d("结束签到数据接口 " + url);
        OkHttpUtils.getWithoutParam(url, new CommonCallback(mHandler));
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == ResultConstants.RESULT_OK) {
                Map<String, String> responseResult = JsonUtils.responseResult(message.obj.toString());
                String s = responseResult.get("data");
                Sign sign = JsonUtils.jsonToPojo(s, Sign.class);
                LogUtils.d("统计签到成功 " + sign);
                tvSignTotal.setText(" 签到人数 ：" + sign.getTotal());
                lvSignStudent.setAdapter(new SignResultAdapter(sign));
            }
            return false;
        }
    });


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_sign) {// 点击结束
            btnSign.setText("签到完成");
            btnSign.setEnabled(false);
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
