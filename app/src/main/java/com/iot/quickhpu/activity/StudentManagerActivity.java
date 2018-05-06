package com.iot.quickhpu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.StudentInfoAdapter;
import com.iot.quickhpu.pojo.Student;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.ToastUtils;

import java.util.List;

public class StudentManagerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lvStudentInfo;
    private List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        initView();
        initEvent();
        initData();
    }

    private void initData() {
        String studentInfo = getIntent().getStringExtra("studentInfo");
        LogUtils.d(">>>>>>>>>>>>学生管理页面接收到>>>>>>>>  " + studentInfo);
        studentList = JSONArray.parseArray(studentInfo, Student.class);
        LogUtils.d(">>>>>>>>>>>>JSON转换>>>>>>  " + studentList.toString());
        lvStudentInfo.setAdapter(new StudentInfoAdapter(studentList));
    }

    private void initEvent() {
        lvStudentInfo.setOnItemClickListener(this);
    }

    private void initView() {
        lvStudentInfo = findViewById(R.id.list_view_student_info);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // 拿到学生详细数据
        // 页面跳转至 StudentDetailActivity
        ToastUtils.showLong(this,studentList.get(i).toString());
        ActivityUtils.toAnotherActivityWithData(this,StudentDetailActivity.class,"studentDetail",studentList.get(i));
    }
}
