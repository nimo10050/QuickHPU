package com.iot.quickhpu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Student;
import com.iot.quickhpu.utils.FileUtils;
import com.iot.quickhpu.utils.LogUtils;

import java.io.LineNumberInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class StudentDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etStudentName;
    private EditText etStudentBirthday;

    private Button btnSave;
    private Button btnDelete;

    private Student studentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        studentDetail = (Student) getIntent().getSerializableExtra("studentDetail");
        LogUtils.d(">>>>>学生详情>>>>> " + studentDetail.toString());
        initView();
        initData();
        inEvent();
    }

    private void inEvent() {
        btnDelete.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void initData() {
        etStudentName.setText(studentDetail.getName());
        etStudentBirthday.setText(studentDetail.getBirthday());

    }

    private void initView() {
        etStudentName = findViewById(R.id.et_student_name);
        etStudentBirthday = findViewById(R.id.et_student_birthday);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_delete:// 删除学生信息
                deleteStudent();
                finish();
                break;
            case R.id.btn_save:// 保存学生信息

                break;
        }
    }

    private void deleteStudent() {
        LogUtils.d(">>>>>>>>>>>删除学生信息>>>>>>>.");
        // 读取json文件
        String json = FileUtils.readJsonFile(this, "test.json");
        // 转化为集合
        List<Student> list = JSONArray.parseArray(json, Student.class);
        // 删除元素
        Iterator<Student> iterator = list.iterator();
        while(iterator.hasNext()){
            Student student = iterator.next();
            if (studentDetail.getName().equals(student.getName())){

                iterator.remove();
                LogUtils.d(">>>>>>>>>>>删除学生信息  成功>>>>>>>.");
            }
        }
        // 转化为json字符串
        String jsonString = JSONArray.toJSONString(list);
        // 写入文件
        FileUtils.writeJsonFile(this,"test.json",jsonString);

    }
}
