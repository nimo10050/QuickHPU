package com.iot.quickhpu.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Student;

import dmax.dialog.SpotsDialog;

public class InfoDetailActivity extends AppCompatActivity {


    private TextView tvStudentId;
    private TextView tvStudentName;
    private TextView tvStudentSex;
    private TextView tvInstitute;
    private TextView tvMajor;
    private TextView tvGrade;
    private TextView tvClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        initView();
        initData();
    }

    private void initView() {

          tvStudentId = findViewById(R.id.tv_student_id);
          tvStudentName = findViewById(R.id.tv_student_name);
          tvStudentSex=findViewById(R.id.tv_student_sex);
          tvInstitute=findViewById(R.id.tv_student_institute);
          tvMajor = findViewById(R.id.tv_student_major);
          tvGrade = findViewById(R.id.tv_student_grade);
          tvClass = findViewById(R.id.tv_student_studentClass);
    }

    // 初始化数据
    //  todo 身份证号换成学生证号
    private void initData() {
        Intent intent = getIntent();
        Student studentInfo = (Student) intent.getSerializableExtra("info");
        System.out.println(studentInfo.toString());
        tvStudentId.setText(studentInfo.getStudentId());
        tvStudentName.setText(studentInfo.getName());
        tvStudentSex.setText(studentInfo.getSex());
        tvInstitute.setText(studentInfo.getInstitute());
        tvMajor.setText(studentInfo.getMajor());
        tvGrade.setText(studentInfo.getGrade());
        tvClass.setText(studentInfo.getStudentClass());
    }

}
