package com.iot.quickhpu.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iot.quickhpu.R;
import com.iot.quickhpu.activity.ClassManagerActivity;
import com.iot.quickhpu.activity.EmptyClassroomActivity;
import com.iot.quickhpu.activity.GradeActivity;
import com.iot.quickhpu.activity.GradeAllActivity;
import com.iot.quickhpu.activity.SignActivity;
import com.iot.quickhpu.activity.StudentManagerActivity;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.FileUtils;
import com.iot.quickhpu.utils.LogUtils;


/**
 * @作者 lozon
 * @日期 2018/2/21
 * @描述 主页Fragment
 */
public class FunctionFragment extends Fragment implements View.OnClickListener {

    // private Context mContext;

    public FunctionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_function, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //mContext = getActivity();
        // 主页菜单栏
        //GridView mainGridView = view.findViewById(R.id.main_gridview);
        //mainGridView.setAdapter(new MainGridViewAdapter(context));
        LinearLayout grade = view.findViewById(R.id.ll_func_grade);
        LinearLayout gradeAll = view.findViewById(R.id.ll_func_grade_all);
        LinearLayout emptyClassroom = view.findViewById(R.id.ll_func_empty_classroom);
        LinearLayout classManager = view.findViewById(R.id.ll_func_class_manager);
        LinearLayout lessonSign = view.findViewById(R.id.ll_func_lesson_sign);
        grade.setOnClickListener(this);
        gradeAll.setOnClickListener(this);
        emptyClassroom.setOnClickListener(this);
        classManager.setOnClickListener(this);
        lessonSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ll_func_grade:// 本学期成绩
                ActivityUtils.toAnotherActivity(this.getActivity(), GradeActivity.class);
                break;
            case R.id.ll_func_grade_all://历年成绩
                ActivityUtils.toAnotherActivity(this.getActivity(), GradeAllActivity.class);
                break;
            case R.id.ll_func_empty_classroom:// 空教室查询
                ActivityUtils.toAnotherActivity(this.getActivity(), EmptyClassroomActivity.class);
                break;
            case R.id.ll_func_class_manager:// 班级管理
                String json = FileUtils.readJsonFile(getActivity(), "test.json");
                ActivityUtils.toAnotherActivityWithData(this.getActivity(), ClassManagerActivity.class,"studentInfo", json);
                break;
            case R.id.ll_func_lesson_sign:// 课堂点名
                LogUtils.d("课堂点名");
                ActivityUtils.toAnotherActivity(getActivity(), SignActivity.class);
                break;
        }
    }


}
