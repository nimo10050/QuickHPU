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
import com.iot.quickhpu.adapter.GradeAdapter;
import com.iot.quickhpu.pojo.Grade;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/3
 * @Description 成绩列表弹框
 */

public class GradeDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    private ListView lvGrade;

    private List<Grade> gradeList;

    public GradeDialog(Context context, List<Grade> gradeList, String title, String detail) {
        super(context, R.style.GradeDetailDialogStyle);
        this.gradeList = gradeList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_grade);
        initView();
        initData(gradeList);
    }

    private void initView() {
        setSize();
        TextView tvClose = findViewById(R.id.tv_close_dialog);
        lvGrade = findViewById(R.id.list_view_grade_term);
        tvClose.setOnClickListener(this);
    }

    private void setSize() {
        //获取屏幕宽高
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
//        int width = display.getWidth();
//        int height = display.getHeight();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        //lp.x与lp.y表示相对于原始位置的偏移.
        //将对话框的大小按屏幕大小的百分比设置
        //lp.x = (int) (width * 0.05); // 新位置X坐标
        //lp.y = (int) (height * 0.2); // 新位置Y坐标
       // lp.width = (int) (width * 0.9); // 宽度
        //lp.height = (int) (height * 0.9); // 高度
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

    private void initData(List<Grade> gradeList) {
        lvGrade.setAdapter(new GradeAdapter(gradeList));
    }
}
