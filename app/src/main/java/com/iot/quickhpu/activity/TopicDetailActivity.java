package com.iot.quickhpu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.utils.ActivityUtils;

import org.w3c.dom.Text;

import java.io.Serializable;

public class TopicDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        Intent intent = getIntent();
        topic = (Topic) intent.getSerializableExtra("TOPIC_DETAIL");

        TextView tvDetailTitle = findViewById(R.id.tv_detail_title);
        TextView tvDetailContent = findViewById(R.id.tv_detail_content);
        TextView tvDetailDate = findViewById(R.id.tv_detail_date);
        TextView tvDetailUsername = findViewById(R.id.tv_detail_username);

        Button btnPublish = findViewById(R.id.btn_publish_comment);
        Button btnShow = findViewById(R.id.btn_show_comment);

        btnPublish.setOnClickListener(this);
        btnShow.setOnClickListener(this);


        tvDetailTitle.setText(topic.getTitle());
        tvDetailContent.setText(topic.getContent());
        tvDetailDate.setText(topic.getDate());
        tvDetailUsername.setText(topic.getUsername());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_publish_comment:
                // 进入发布评论页面
                ActivityUtils.toAnotherActivity(this
                        ,PublishCommentActivity.class);
                break;
            case R.id.btn_show_comment:
                // 显示评论列表
                ActivityUtils.toAnotherActivity(this
                        ,CommentListActivity.class);
                break;
        }
    }
}
