package com.iot.quickhpu.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.GradeAdapter;
import com.iot.quickhpu.callback.GradeCallback;
import com.iot.quickhpu.callback.MyCallback;
import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.view.GradeDetailDialog;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class GradeActivity extends AppCompatActivity {

    private ListView listViewGrade;

    private AlertDialog loadingDialog;

    private List<Grade> gradeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        initView();
        initEvent(this);
        initData1();
    }

    private void initEvent(final Context context) {
        listViewGrade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GradeDetailDialog gradeDetailDialog = new GradeDetailDialog(view.getContext(),gradeList,i,"标题","详情");
                gradeDetailDialog.show();
            }
        });
    }

//    private void initData(final AlertDialog loadingDialog) {
//        gradeList = new ArrayList<>();
//        OkHttpUtils.getDataByCookie(URLConstants.FINAL_GRADE, new GradeCallback(mHandler, new MyCallback() {
//                    @Override
//                    public void onComplete(Object object) {
//                        gradeList = (List<Grade>) object;
//                        GradeAdapter adapter = new GradeAdapter(gradeList);
//                        listViewGrade.setAdapter(adapter);
//                        loadingDialog.dismiss();
//                    }
//                })
//                , (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie"));
//    }

    private void initData1() {
        gradeList = new ArrayList<>();
        OkHttpUtils.getDataByCookie(URLConstants.FINAL_GRADE, new GradeCallback(mHandler, null)
                , (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie"));
    }

    private void initView() {
        loadingDialog = new SpotsDialog(this);
        listViewGrade = findViewById(R.id.list_view_grade);
        loadingDialog.show();

    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case GradeConstants.GRADE_RESULT:
                    // miss掉加载框
                    gradeList = (List<Grade>) message.obj;
                    GradeAdapter adapter = new GradeAdapter(gradeList);
                    listViewGrade.setAdapter(adapter);
                    loadingDialog.dismiss();
            }
            return false;
        }
    });
}
