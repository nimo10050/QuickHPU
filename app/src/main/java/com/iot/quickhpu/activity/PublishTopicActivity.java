package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.utils.IDUtils;
import com.iot.quickhpu.utils.StringUtils;
import com.iot.quickhpu.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PublishTopicActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTopicTitle;
    private EditText etTopicContent;
    private Button btnTopicPublish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_topic);

        initView();
        initEvent();

    }

    private void initEvent() {
        btnTopicPublish.setOnClickListener(this);
    }

    private void initView() {
        etTopicTitle = findViewById(R.id.et_topic_title);
        etTopicContent = findViewById(R.id.et_topic_content);
        btnTopicPublish = findViewById(R.id.btn_topic_publish);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_topic_publish) {
            // 获取标题
            String title = etTopicTitle.getText().toString();
            // 获取内容
            String content = etTopicContent.getText().toString();
            // 校验不能为空
            Map<String, String> errors = validateTopicForm(title, content);
            if (errors.size() > 0) {
                return;
            } else {
                // 生成id
                Long topicId = IDUtils.genItemId();
                // 获得当前日期
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                date.format(new Date());
                // 转化为pojo对象
                Topic topic = new Topic(topicId.toString(), title, content, "用户名", date.toString());
                // post请求发布
                // TODO 访问网络
            }
        }
    }

    private Map<String, String> validateTopicForm(String title, String content) {
        Map<String, String> errors = new HashMap<>();
        if (!StringUtils.isNotEmpty(title)) {
            errors.put("title", "标题不能为空");
            ToastUtils.showShort(this, "标题不能为空");
            return errors;
        }
        if (!StringUtils.isNotEmpty(content)) {
            errors.put("content", "内容不能为空");
            return errors;
        }
        return errors;
    }
}
