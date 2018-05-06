package com.iot.quickhpu.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.activity.InfoDetailActivity;
import com.iot.quickhpu.callback.InfoCallback;
import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Student;
import com.iot.quickhpu.utils.ActivityUtils;
import com.iot.quickhpu.utils.InfoJsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.ToastUtils;

import org.w3c.dom.Text;

import dmax.dialog.SpotsDialog;

/**
 * 个人信息页面
 */
public class InfoFragment extends Fragment implements View.OnClickListener{

    AlertDialog loadingDialog ;
    TextView tvInfoName;
    TextView tvInfoMajor;
    TextView tvInfoInstitute;
    private Student student;


    public InfoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 初始化 view
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        CardView cvInfo = view.findViewById(R.id.card_view);
        tvInfoName = cvInfo.findViewById(R.id.cv_tv_info_name);
        tvInfoInstitute = cvInfo.findViewById(R.id.cv_tv_info_institute);
        tvInfoMajor = cvInfo.findViewById(R.id.cv_tv_info_major);


        cvInfo.setOnClickListener(this);

        // cvInfo
        loadingDialog = new SpotsDialog(getActivity());
        loadingDialog.show();

        //请求网络
        OkHttpUtils.getDataByCookie(URLConstants.STUDENT_INFO
                        ,new InfoCallback(mHandler,null)
                        ,(String) SpUtils.get(getActivity()
                        , LoginConstants.LOGIN_COOKIE
                        ,"cookie")
        );
        return view;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what==1){
                String obj = (String)message.obj;
                LogUtils.d(">>>>>>>>>>接收到用户信息>>>>" + obj);
                 student = InfoJsonUtils.jsonToObject(obj);
                initInfoData(student);
                ToastUtils.showShort(getActivity(),student.toString());
                loadingDialog.dismiss();
            }
            return false;
        }
    });

    private void initInfoData(Student student) {
        tvInfoName.setText(student.getName());
        tvInfoMajor.setText(student.getMajor());
        tvInfoInstitute.setText(student.getInstitute());
    }

    @Override
    public void onClick(View view) {
        ActivityUtils.toAnotherActivityWithData(getActivity()
                , InfoDetailActivity.class
                ,"info"
                ,student
        );
    }
}
