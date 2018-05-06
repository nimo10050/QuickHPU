package com.iot.quickhpu.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.PointerIcon;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.TermAdapter;
import com.iot.quickhpu.callback.GradeAllCallback;
import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.view.GradeDialog;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class GradeAllActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private AlertDialog loadingDialog;
    private List<List<Grade>> gradeAllList;
    private ListView lvTerm;
    private GradeDialog gradeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_all);
        initView();
        initEvent();
        initData();
    }

    private void initEvent() {
        lvTerm.setOnItemClickListener(this);

    }

    private void initData() {

        OkHttpUtils.getDataByCookie(URLConstants.ALL_GRADE, new GradeAllCallback(mHandler)
                , (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie"));
    }

    private void initView() {
        loadingDialog = new SpotsDialog(this);
        lvTerm = findViewById(R.id.list_view_term);
        loadingDialog.show();

    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case GradeConstants.GRADE_RESULT:
                    gradeAllList = (List<List<Grade>>) message.obj;
                    // TODO: 将数据填充到view
                    LogUtils.d("历年成绩: " + gradeAllList.size());
                    LogUtils.d("大一上成绩: " + JsonUtils.objectToJson(gradeAllList.get(0)));
                    createViewAndFillData(gradeAllList);
                    // miss掉加载框
                    loadingDialog.dismiss();
            }
            return false;
        }
    });

    private void createViewAndFillData(List<List<Grade>> gradeAllList) {
        lvTerm.setAdapter(new TermAdapter(gradeAllList));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LogUtils.d("点击获取item数据:  " + gradeAllList.get(i).size());
        gradeDialog = new GradeDialog(this,gradeAllList.get(i),"标题","细节");
        gradeDialog.show();
    }
}
