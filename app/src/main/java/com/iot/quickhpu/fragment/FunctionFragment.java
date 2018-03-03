package com.iot.quickhpu.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.iot.quickhpu.R;
import com.iot.quickhpu.activity.GradeActivity;
import com.iot.quickhpu.activity.MainActivity;
import com.iot.quickhpu.adapter.MainGridViewAdapter;
import com.iot.quickhpu.utils.ActivityUtils;


/**
 * @作者 lozon
 * @日期 2018/2/21
 * @描述 主页Fragment
 */
public class FunctionFragment extends Fragment {

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
        final Activity context = getActivity();
        // 主页菜单栏
        //GridView mainGridView = view.findViewById(R.id.main_gridview);
        //mainGridView.setAdapter(new MainGridViewAdapter(context));
        LinearLayout grade = view.findViewById(R.id.ll_func_grade);
        grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.ll_func_grade){
                    ActivityUtils.toAnotherActivity(context, GradeActivity.class);
                }
            }
        });
    }
}
