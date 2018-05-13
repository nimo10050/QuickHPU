package com.iot.quickhpu.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.ClassManagerAdapter;
import com.iot.quickhpu.adapter.GradeAdapter;
import com.iot.quickhpu.callback.ListClassCallback;
import com.iot.quickhpu.callback.NewClassCallback;
import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.constants.SpConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassManagerActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ListView lvMyClass;
    private TextView tvCreateClass;

    private List<String> classList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);
        initView();
        initData();
    }

    private void initData() {
        // 请求数据
        String code = (String) SpUtils.get(this, SpConstants.USER_CODE, "1234");
        String url = URLConstants.CLASS_MANAGER_URL + "list/" + code + ".do";
        OkHttpUtils.requestLocal(url, new ListClassCallback(mHandler));

    }

    private void initView() {
        lvMyClass = findViewById(R.id.lv_class_list);
        tvCreateClass = findViewById(R.id.tv_create_class);
        tvCreateClass.setOnClickListener(this);
        lvMyClass.setOnItemClickListener(this);
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    // 获取消息
                    String json = (String) message.obj;
                    Map<String, String> result = JsonUtils.responseResult(json);
                    // 解析数据
                    String data = result.get("data");
                    try {
                        JSONArray array = new JSONArray(data);
                        classList = new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            String s = array.getString(i);
                            org.json.JSONObject object = new org.json.JSONObject(s);
                            classList.add(object.getString("name"));
                        }
                        lvMyClass.setAdapter(new ClassManagerAdapter(classList));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ToastUtils.showShort(ClassManagerActivity.this, (String) message.obj);
                    break;
            }
            return false;
        }
    });

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_create_class) {
            ActivityUtils.toAnotherActivity(this, CreateClassActivity.class);
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ActivityUtils.toAnotherActivityWithData(this
                ,StudentManagerActivity.class
                ,"className"
                ,classList.get(i));
    }
}
