package com.iot.quickhpu.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.quickhpu.R;
import com.iot.quickhpu.activity.PublishTopicActivity;
import com.iot.quickhpu.activity.TopicDetailActivity;
import com.iot.quickhpu.adapter.TopicAdapter;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.FileUtils;
import com.iot.quickhpu.utils.HPUResult;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * 话题区
 */
public class QuanziFragment extends Fragment implements View.OnClickListener {

    private ListView lvTopic;

    List<Topic> topicList;

    private FloatingActionButton fabPublishTopic;

    public QuanziFragment() {
        LogUtils.d("  初始化话题列表页面 ");
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                String json = (String) message.obj;
                HPUResult result = JSONObject.parseObject(json, HPUResult.class);
                if (result.getData() != null) {
                    String s = JSONArray.toJSONString(result.getData());
                    topicList = JSONArray.parseArray(s, Topic.class);
                    lvTopic.setAdapter(new TopicAdapter(topicList));
                } else {
                    ToastUtils.showLong(getActivity(), "数据为空");
                }
            } else {
                ToastUtils.showLong(getActivity(), message.obj.toString());
            }
            return false;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanzi, container, false);
        initView(view);
        initEvent();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(" onResume 加载话题列表数据");
        initData();
    }

    private void initData() {
        // 访问网路
        String url = URLConstants.TOPIC_MANAGER_URL + "list.do";
        LogUtils.d(" 开始请求话题列表接口 : " + url);
        OkHttpUtils.getWithoutParam(url, new CommonCallback(mHandler));
    }

    private void initView(View view) {
        lvTopic = view.findViewById(R.id.list_view_topic);
        fabPublishTopic = view.findViewById(R.id.fab_publish_topic);
    }

    private void initEvent() {
        lvTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpUtils.put(getActivity(), "CURRENT_TOPIC_ID", topicList.get(i).getTopicId());
                // 进入话题详情页面
                ActivityUtils.toAnotherActivityWithData(getActivity()
                        , TopicDetailActivity.class
                        , "TOPIC_DETAIL", topicList.get(i));
            }
        });
        fabPublishTopic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_publish_topic) {
            ActivityUtils.toAnotherActivity(getActivity(), PublishTopicActivity.class);
        }
    }

}
