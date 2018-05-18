package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.utils.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class JoinSignActivity extends BaseActivity {

    @BindView(R.id.et_join_code)
    EditText etJoinCode;
    @BindView(R.id.btn_sign_join)
    Button btnSignJoin;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_sign);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        initData();
    }

    private void initData() {
        String userCode = getUserCode();
        String url = URLConstants.SIGN_MANAGER_URL+"join/"+userCode+".do";
        OkHttpUtils.getWithoutParam(url,new CommonCallback(mHandler));
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            return false;
        }
    });


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
