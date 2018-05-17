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
import com.iot.quickhpu.activity.LibraryActivity;
import com.iot.quickhpu.activity.SchoolNewsActivity;
import com.iot.quickhpu.activity.SignActivity;
import com.iot.quickhpu.activity.SportGradeActivity;
import com.iot.quickhpu.activity.StudentManagerActivity;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.FileUtils;
import com.iot.quickhpu.utils.LogUtils;


/**
 * @作者 lozon
 * @日期 2018/2/21
 * @描述 功能页 Fragment
 */
public class FunctionFragment extends Fragment implements View.OnClickListener {


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

        LinearLayout grade = view.findViewById(R.id.ll_func_grade);
        LinearLayout gradeAll = view.findViewById(R.id.ll_func_grade_all);
        LinearLayout emptyClassroom = view.findViewById(R.id.ll_func_empty_classroom);
        LinearLayout classManager = view.findViewById(R.id.ll_func_class_manager);
        LinearLayout lessonSign = view.findViewById(R.id.ll_func_lesson_sign);
        LinearLayout schoolNews = view.findViewById(R.id.ll_func_school_news);
        LinearLayout library = view.findViewById(R.id.ll_func_library);
        LinearLayout sport = view.findViewById(R.id.ll_func_sport);

        grade.setOnClickListener(this);
        gradeAll.setOnClickListener(this);
        emptyClassroom.setOnClickListener(this);
        classManager.setOnClickListener(this);
        lessonSign.setOnClickListener(this);
        schoolNews.setOnClickListener(this);
        library.setOnClickListener(this);
        sport.setOnClickListener(this);
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
                ActivityUtils.toAnotherActivity(this.getActivity(), ClassManagerActivity.class);
                break;
            case R.id.ll_func_lesson_sign:// 课堂点名
                LogUtils.d("课堂点名");
                ActivityUtils.toAnotherActivity(getActivity(), SignActivity.class);
                break;
            case R.id.ll_func_school_news:// 校园新闻
                ActivityUtils.toAnotherActivity(getActivity(), SchoolNewsActivity.class);
                break;
            case R.id.ll_func_library:// 图书查询
                LogUtils.d("图书查询");
                ActivityUtils.toAnotherActivity(getActivity(), LibraryActivity.class);
                break;
            case R.id.ll_func_sport://体能测试
                LogUtils.d("体能测试");
                ActivityUtils.toAnotherActivity(getActivity(), SportGradeActivity.class);
        }
    }


}
