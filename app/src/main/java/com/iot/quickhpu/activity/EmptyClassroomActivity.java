package com.iot.quickhpu.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.EmptyClassroomListCallback;
import com.iot.quickhpu.callback.GradeAllCallback;
import com.iot.quickhpu.constants.EmptyClassroomConstants;
import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.EmptyClassroom;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;
import com.iot.quickhpu.view.EmptyClassroomListDialog;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class EmptyClassroomActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNumber1Building;
    private TextView tvNumber2Building;
    private TextView tvNumber3Building;

    // 弹框
    private EmptyClassroomListDialog emptyClassroomListDialog;
    //private AlertDialog loadingDialog;
    // 数据
    private List<EmptyClassroom> emptyClassroomList;
    // 加载框
     private AlertDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_classroom);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        /*OkHttpUtils.getDataByCookieAndParams(URLConstants.EMPTY_CLASSROOM, new EmptyClassroomListCallback(mHandler)
                , (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie")
                ,"building"
                ,"1");*/
    }

    private void initEvent() {
        tvNumber1Building.setOnClickListener(this);
        tvNumber2Building.setOnClickListener(this);
        tvNumber3Building.setOnClickListener(this);
    }


    private void initView() {

        tvNumber1Building = findViewById(R.id.tv_number_1_building);
        tvNumber2Building = findViewById(R.id.tv_number_2_building);
        tvNumber3Building = findViewById(R.id.tv_number_3_building);

        loadingDialog = new SpotsDialog(this);

    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case EmptyClassroomConstants.EMPTY_CLASSROOM_RESULT:
                    emptyClassroomList = (List<EmptyClassroom>) message.obj;
                    LogUtils.d("空教室查询结果 >>>>>>> : " + JsonUtils.objectToJson(emptyClassroomList.get(0)));
                    loadingDialog.dismiss();
                    emptyClassroomListDialog = new EmptyClassroomListDialog(EmptyClassroomActivity.this, emptyClassroomList, "标题", "细节");
                    emptyClassroomListDialog.show();
            }
            return false;
        }
    });

    @Override
    public void onClick(View view) {
        int id = view.getId();
        loadingDialog.show();
        switch (id) {
            case R.id.tv_number_1_building:
                ToastUtils.showLong(this,"一号教学楼");
                // 访问网络
                OkHttpUtils.getDataByCookieAndParams(URLConstants.EMPTY_CLASSROOM, new EmptyClassroomListCallback(mHandler)
                        , (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie")
                        ,"building"
                        ,"1");

                break;
            case R.id.tv_number_2_building:
                ToastUtils.showLong(this,"二号教学楼");
                // 访问网络
                OkHttpUtils.getDataByCookieAndParams(URLConstants.EMPTY_CLASSROOM, new EmptyClassroomListCallback(mHandler)
                        , (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie")
                        ,"building"
                        ,"2");
                break;
            case R.id.tv_number_3_building:
                ToastUtils.showLong(this,"三号教学楼");
                // 访问网络
                OkHttpUtils.getDataByCookieAndParams(URLConstants.EMPTY_CLASSROOM, new EmptyClassroomListCallback(mHandler)
                        , (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie")
                        ,"building"
                        ,"3");
                break;
        }
    }
}
