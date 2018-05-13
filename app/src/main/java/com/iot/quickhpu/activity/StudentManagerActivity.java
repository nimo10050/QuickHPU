package com.iot.quickhpu.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.ClassManagerAdapter;
import com.iot.quickhpu.adapter.StudentInfoAdapter;
import com.iot.quickhpu.callback.ListStudentCallback;
import com.iot.quickhpu.constants.SpConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Student;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.JsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentManagerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvStudentInfo;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        initView();
        initEvent();
        initData();
    }

    private void initData() {
        String title = getIntent().getStringExtra("className");
        String code = (String) SpUtils.get(this, SpConstants.USER_CODE, "1234");
        String url = URLConstants.STUDENT_MANAGER_URL + "list" + "/" + code + ".do?=" + title;
        // 请求网络
        OkHttpUtils.requestLocal(url, new ListStudentCallback(mHandler));

    }

    private void initEvent() {
        lvStudentInfo.setOnItemClickListener(this);
    }

    private void initView() {
        lvStudentInfo = findViewById(R.id.list_view_student_info);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // 拿到学生详细数据
        // 页面跳转至 StudentDetailActivity
        ToastUtils.showLong(this, studentList.get(i).toString());
        ActivityUtils.toAnotherActivityWithData(this, StudentDetailActivity.class, "studentDetail", studentList.get(i));
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
                    List<Student> list = JSONArray.parseArray(data, Student.class);
                    LogUtils.d(">>>>>>>>>断点 StudentManagerActivity");
                    if (list==null||list.size()==0){
                        return false;
                    }
                    lvStudentInfo.setAdapter(new StudentInfoAdapter(list));
                    break;
            }
            return false;
        }
    });


}
