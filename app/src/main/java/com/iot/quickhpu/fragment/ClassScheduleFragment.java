package com.iot.quickhpu.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
 * @作者 LOZON
 * @日期 2018/5/16
 * @描述 课程表页面
 */
public class ClassScheduleFragment extends Fragment implements View.OnClickListener {

    private TimetableView mTimetableView;
    private TextView tvPreWeek;
    private TextView tvNextWeek;
    private TextView tvCurrentWeek;

    private List<SubjectBean> subjectBeans;

    private int currentWeek = 1;

    public ClassScheduleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_class_schedule, container, false);
        // 课程表
        mTimetableView = (TimetableView) view.findViewById(R.id.id_timetableView);
        tvPreWeek = view.findViewById(R.id.tv_pre_week);
        tvNextWeek = view.findViewById(R.id.tv_next_week);
        tvCurrentWeek = view.findViewById(R.id.tv_current_week);

        tvCurrentWeek.setText("第" + currentWeek + " 周");

        tvPreWeek.setOnClickListener(this);
        tvNextWeek.setOnClickListener(this);

        subjectBeans = transform(MySubjectModel.loadDefaultSubjects());
        mTimetableView.setDataSource(subjectBeans)
                //.setCurTerm("大三上学期")
                .setCurWeek(currentWeek)
                //.setOnSubjectItemClickListener(getActivity())
                .showTimetableView();

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
            subjectBeans.add(new SubjectBean(mySubject.getName(), mySubject.getRoom(), mySubject.getTeacher(), mySubject.getWeekList(),
                    mySubject.getStart(), mySubject.getStep(), mySubject.getDay(), color, mySubject.getTime()));
        }
        return subjectBeans;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_pre_week:
                currentWeek = (--currentWeek) < 1 ? 1 : currentWeek;
                tvCurrentWeek.setText("第" + currentWeek + " 周");
                changeWeek(currentWeek);
                break;
            case R.id.tv_next_week:
                currentWeek = (++currentWeek) > 22 ? 22 : currentWeek;
                tvCurrentWeek.setText("第" + currentWeek + " 周");
                changeWeek(currentWeek);
                break;
        }
    }

    private void changeWeek(int week) {
        subjectBeans = transform(MySubjectModel.loadDefaultSubjects());
        mTimetableView.setDataSource(subjectBeans)
                //.setCurTerm("大三上学期")
                .setCurWeek(week)
                //.setOnSubjectItemClickListener(getActivity())
                .showTimetableView();
    }

}
