package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.CommentAdapter;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Comment;
import com.iot.quickhpu.utils.HPUResult;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;

import java.util.List;

public class CommentListActivity extends AppCompatActivity {

    private List<Comment> commentList;

    private String topicId = "111";

    private ListView lvComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        lvComment = findViewById(R.id.lv_comment);

    }

    @Override
    protected void onResume() {
        super.onResume();
        topicId = (String) SpUtils.get(this,"CURRENT_TOPIC_ID","311409080228");
        initData();
    }

    private void initData() {
        String url = URLConstants.TOPIC_LIST_URL + "list/comment/" + topicId + ".do";
        System.out.println(">>>>>>>>>>>CommentList URL     " + url);
        OkHttpUtils.getWithoutParam(url, new CommonCallback(mHandler));
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            String json = (String) message.obj;
            System.out.println(">>>>>>>>>>>>>   CommentList   JSON " + json);
            HPUResult result = JSONObject.parseObject(json, HPUResult.class);
            if (result.getData() != null) {
                String s = JSONArray.toJSONString(result.getData());
                commentList = JSONArray.parseArray(s, Comment.class);
                lvComment.setAdapter(new CommentAdapter(commentList));
            }
            return false;
        }
    });

}
