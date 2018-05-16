package com.iot.quickhpu.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.ResultConstants;
import com.iot.quickhpu.constants.SpConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Comment;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.CommonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;

public class PublishCommentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCommentContent;

    private String topicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_comment);

        topicId = (String) SpUtils.get(this,"CURRENT_TOPIC_ID","311409080228");

        Button btnPublish = findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(this);

        etCommentContent = findViewById(R.id.et_comment_content);
    }

    @Override
    public void onClick(View view) {
        String commentContent = etCommentContent.getText().toString();
        String userId = (String) SpUtils.get(this, SpConstants.USER_CODE, "1234");
        String username = (String) SpUtils.get(this, SpConstants.USER_NICK_NAME, "1234");
        String d = CommonUtils.getCurrentDate();

        Comment comment = new Comment(userId, "", commentContent, d, username);
        String s = JSONObject.toJSONString(comment);

        String url = URLConstants.TOPIC_LIST_URL + "publish/comment/" + topicId + ".do";

        OkHttpUtils.postWithJsonParam(url, s, new CommonCallback(mHandler));
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == ResultConstants.RESULT_OK) {
                LogUtils.d("发布评论成功");
                ActivityUtils.toAnotherActivity(PublishCommentActivity.this, CommentListActivity.class);
                PublishCommentActivity.this.finish();
            }
            return false;
        }
    });


}
