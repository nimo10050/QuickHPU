package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.iot.quickhpu.R;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.ResultConstants;
import com.iot.quickhpu.constants.SpConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Student;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.StringUtils;
import com.iot.quickhpu.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StudentAddActivity extends AppCompatActivity {

    @BindView(R.id.et_add_student_id)
    EditText etAddStudentId;
    @BindView(R.id.et_add_student_name)
    EditText etAddStudentName;
    @BindView(R.id.et_add_student_sex)
    EditText etAddStudentSex;
    @BindView(R.id.et_add_student_major)
    EditText etAddStudentMajor;
    @BindView(R.id.et_add_student_xueyuan)
    EditText etAddStudentXueyuan;
    @BindView(R.id.btn_add_student)
    Button btnAddStudent;

    Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);
        bind = ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_add_student)
    public void onViewClicked() {
        String studentId = etAddStudentId.getText().toString().trim();
        String studentName = etAddStudentName.getText().toString().trim();
        String sex = etAddStudentSex.getText().toString().trim();
        String major = etAddStudentMajor.getText().toString().trim();
        String xueyuan = etAddStudentXueyuan.getText().toString().trim();

        boolean success = checkInput(studentId, studentName, sex, major, xueyuan);
        if (!success) {
            ToastUtils.showLong(this, "信息未填写完整");
            return;
        }
        Student student = new Student();
        student.setSex(sex);
        student.setInstitute(xueyuan);
        student.setMajor(major);
        student.setStudentId(studentId);
        student.setName(studentName);
        String json = JSONObject.toJSONString(student);

        // 拼接url
        String code = (String) SpUtils.get(this, SpConstants.USER_CODE, "311409080228");
        String title = (String) SpUtils.get(this, SpConstants.CLASS_NAME, "544");
        String url = URLConstants.STUDENT_MANAGER_URL + "add/" + code + ".do?title=" + title;
        LogUtils.d("添加学生的数据接口 : " + url);
        OkHttpUtils.postWithJsonParam(url, json, new CommonCallback(mHandler));
    }

    private boolean checkInput(String... studentInfo) {
        for (int i = 0; i < studentInfo.length; i++) {
            if (!StringUtils.isNotEmpty(studentInfo[i])) {
                return false;
            }
        }
        return true;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == ResultConstants.RESULT_OK) {
                ToastUtils.showLong(StudentAddActivity.this, "添加成功");
                LogUtils.d("添加学生成功 " + message.obj);
                StudentAddActivity.this.finish();
            } else {
                LogUtils.d("添加学生失败 " + message.obj);
            }
            return false;
        }
    });


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
