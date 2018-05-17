package com.iot.quickhpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.SchoolNews;

public class SchoolNewsDetailActivity extends BaseActivity {

    public static final String BASE_URL = "http://news.hpu.edu.cn/news/contents/544/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_news_detail);
        Intent intent = getIntent();
        SchoolNews schoolNews = (SchoolNews) intent.getSerializableExtra("school_news_detail");

        WebView schoolNewsHtml = findViewById(R.id.wv_school_news_detail);
        WebSettings wSet = schoolNewsHtml.getSettings();
        wSet.setJavaScriptEnabled(true);

        // 设置可以支持缩放
        wSet.setSupportZoom(true);
        // 设置出现缩放工具
        wSet.setBuiltInZoomControls(true);
        //扩大比例的缩放
        wSet.setUseWideViewPort(true);
        //自适应屏幕
        wSet.setLoadWithOverviewMode(true);

        schoolNewsHtml.loadUrl(BASE_URL + schoolNews.getId());
    }
}
