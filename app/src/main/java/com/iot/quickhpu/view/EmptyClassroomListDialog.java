package com.iot.quickhpu.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.EmptyClassroomAdapter;
import com.iot.quickhpu.adapter.GradeAdapter;
import com.iot.quickhpu.pojo.EmptyClassroom;
import com.iot.quickhpu.pojo.Grade;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/3
 * @Description 成绩列表弹框
 */

public class EmptyClassroomListDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    private ListView lvEmptyClassroom;

    private List<EmptyClassroom> emptyClassroomList;

    public EmptyClassroomListDialog(Context context, List<EmptyClassroom> emptyClassroomList, String title, String detail) {
        super(context, R.style.GradeDetailDialogStyle);
        this.emptyClassroomList = emptyClassroomList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_empty_classroom);
        initView();
        initData(emptyClassroomList);
    }

    private void initView() {
        setSize();
        TextView tvClose = findViewById(R.id.tv_close_dialog);
        lvEmptyClassroom = findViewById(R.id.list_view_empty_classroom);
        tvClose.setOnClickListener(this);
    }

    private void setSize() {
        //获取屏幕宽高
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度

        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_close_dialog:
                this.dismiss();
        }
    }

    private void initData(List<EmptyClassroom> emptyClassroomList) {
        lvEmptyClassroom.setAdapter(new EmptyClassroomAdapter(emptyClassroomList));
    }
}
