package com.iot.quickhpu.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.iot.quickhpu.R;
import com.iot.quickhpu.activity.PublishTopicActivity;
import com.iot.quickhpu.adapter.TopicAdapter;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.FileUtils;

import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanziFragment extends Fragment implements View.OnClickListener{

    private ListView lvTopic;

    private AlertDialog loadingDialog;

    private FloatingActionButton fabPublishTopic;

    public QuanziFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanzi, container, false);
        initView(view);
        initEvent();
        initData();
        return view;
    }

    private void initData() {
        // 访问网路
        String json = FileUtils.readJsonFile(getActivity(), "topic.json");
        List<Topic> topicList = JSONArray.parseArray(json, Topic.class);
        // 填充数据
        lvTopic.setAdapter(new TopicAdapter(topicList));
    }

    private void initView(View view) {
        lvTopic = view.findViewById(R.id.list_view_topic);
        fabPublishTopic = view.findViewById(R.id.fab_publish_topic);
        loadingDialog = new SpotsDialog(getActivity());
        // loadingDialog.show();
    }

    private void initEvent() {
        fabPublishTopic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fab_publish_topic){
            ActivityUtils.toAnotherActivity(getActivity(),PublishTopicActivity.class);
        }
    }
}
