package com.iot.quickhpu.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Grade;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/3
 * @Description 成绩详情弹框
 */

public class GradeDetailDialog extends Dialog implements android.view.View.OnClickListener {

    private Context mContext;

    private TextView tvGradeName;
    private TextView tvGradeTotal;
    private TextView tvGradeRange;
    private TextView tvGradeAvg;
    private TextView tvGradeMax;

    private List<Grade> gradeList;

    private int position;

    public GradeDetailDialog(Context context, List<Grade> gradeList, int position, String title, String detail) {
        super(context, R.style.GradeDetailDialogStyle);
        this.gradeList = gradeList;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_grade_detail);
        initView();
        initData(gradeList);
    }

    private void initView() {
        setSize();
        TextView tvClose = findViewById(R.id.tv_close_dialog);
        tvGradeName = findViewById(R.id.tv_grade_name);
        tvGradeTotal = findViewById(R.id.tv_grade_total);
        tvGradeRange = findViewById(R.id.tv_grade_range);
        tvGradeAvg = findViewById(R.id.tv_grade_avg);
        tvGradeMax = findViewById(R.id.tv_grade_max);
        tvClose.setOnClickListener(this);
    }

    private void setSize() {
        //获取屏幕宽高
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        //lp.x与lp.y表示相对于原始位置的偏移.
        //将对话框的大小按屏幕大小的百分比设置
        lp.x = (int) (width * 0.05); // 新位置X坐标
        lp.y = (int) (height * 0.2); // 新位置Y坐标
        lp.width = (int) (width * 0.8); // 宽度
        lp.height = (int) (height * 0.6); // 高度

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
        tvGradeName.setText(gradeList.get(position).getName());
        tvGradeTotal.setText(gradeList.get(position).getTotal());
        tvGradeRange.setText(gradeList.get(position).getRange());
        tvGradeAvg.setText(gradeList.get(position).getAvg());
        tvGradeMax.setText(gradeList.get(position).getMax());
    }
}
