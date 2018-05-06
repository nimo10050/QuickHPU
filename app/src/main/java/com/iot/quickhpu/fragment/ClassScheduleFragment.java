package com.iot.quickhpu.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.MySubject;
import com.iot.quickhpu.utils.MySubjectModel;
import com.zhuangfei.timetable.core.SubjectBean;
import com.zhuangfei.timetable.core.TimetableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassScheduleFragment extends Fragment {

    private TimetableView mTimetableView;

    private List<SubjectBean> subjectBeans;

    public ClassScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_class_schedule, container, false);
        // 课程表
        mTimetableView=(TimetableView) view.findViewById(R.id.id_timetableView);
        subjectBeans=transform(MySubjectModel.loadDefaultSubjects());
        mTimetableView.setDataSource(subjectBeans)
                .setCurTerm("大三上学期")
                .setCurWeek(1)
                //.setOnSubjectItemClickListener(getActivity())
                .showTimetableView();

        //调用过showSubjectView后需要调用changWeek()
        //第二个参数为true时在改变课表布局的同时也会将第一个参数设置为当前周
        //第二个参数为false时只改变课表布局
        //mTimetableView.changeWeek(curWeek, true);

        return view;
    }

    public List<SubjectBean> transform(List<MySubject> mySubjects) {
        //待返回的集合
        List<SubjectBean> subjectBeans = new ArrayList<>();

        //保存课程名、颜色的对应关系
        Map<String, Integer> colorMap = new HashMap<>();
        int colorCount = 1;

        //开始转换
        for (int i = 0; i < mySubjects.size(); i++) {
            MySubject mySubject = mySubjects.get(i);
            //计算课程颜色
            int color;
            if (colorMap.containsKey(mySubject.getName())) {
                color = colorMap.get(mySubject.getName());
            } else {
                colorMap.put(mySubject.getName(), colorCount);
                color = colorCount;
                colorCount++;
            }
            //转换
            subjectBeans.add(new SubjectBean(mySubject.getName(), mySubject.getRoom(),mySubject.getTeacher(), mySubject.getWeekList(),
                    mySubject.getStart(), mySubject.getStep(), mySubject.getDay(), color,mySubject.getTime()));
        }
        return subjectBeans;
    }

}
