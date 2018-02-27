package com.iot.quickhpu.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iot.quickhpu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassScheduleFragment extends Fragment {


    public ClassScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_class_schedule, container, false);
        // 课程表


        return view;
    }

}
