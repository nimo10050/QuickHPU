package com.iot.quickhpu.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.MainGridViewAdapter;


/**
 * @作者 lozon
 * @日期 2018/2/21
 * @描述 主页Fragment
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Activity context = this.getActivity();
        // 主页菜单栏
        GridView mainGridView = view.findViewById(R.id.main_gridview);
        mainGridView.setAdapter(new MainGridViewAdapter(context));
        return view;
    }

}
