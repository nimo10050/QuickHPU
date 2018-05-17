package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.SchoolNewsAdapter;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.callback.NewClassCallback;
import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.ResultConstants;
import com.iot.quickhpu.constants.SpConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.SchoolNews;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import org.json.JSONObject;

import java.time.temporal.ValueRange;
import java.util.Collections;
import java.util.List;

public class SchoolNewsActivity extends BaseActivity {


    private ListView lvSchoolNews;
    private List<SchoolNews> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_news);
        lvSchoolNews = findViewById(R.id.lv_school_news);
        lvSchoolNews.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        String cookie = (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie");
        String url = URLConstants.SCHOOL_NEWS;
        OkHttpUtils.getDataByCookie(url, new CommonCallback(mHandler), cookie);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try {
                if (msg.what == ResultConstants.RESULT_OK) {
                    String json = (String) msg.obj;
                    LogUtils.d(" 学校新闻响应数据成功 " + json);
                    JSONObject jsonObject = new JSONObject(json);
                    String arr = jsonObject.getString("data");
                    newsList = JSONArray.parseArray(arr, SchoolNews.class);
                    Collections.reverse(newsList);// 日期优先级
                    lvSchoolNews.setAdapter(new SchoolNewsAdapter(newsList));
                } else {
                    ToastUtils.showLong(SchoolNewsActivity.this, "请求数据失败");
                    LogUtils.d("请求失败, 数据无法显示");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }
    });


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        ActivityUtils.toAnotherActivityWithData(this, SchoolNewsDetailActivity.class
                , "school_news_detail", newsList.get(i));
    }
}
