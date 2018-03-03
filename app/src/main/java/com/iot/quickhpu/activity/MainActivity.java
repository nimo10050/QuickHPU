package com.iot.quickhpu.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.quickhpu.R;
import com.iot.quickhpu.fragment.FunctionFragment;
import com.iot.quickhpu.fragment.ClassScheduleFragment;
import com.iot.quickhpu.fragment.InfoFragment;
import com.iot.quickhpu.fragment.QuanziFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 容器页面
    private FunctionFragment funcFragment = new FunctionFragment();
    private ClassScheduleFragment classScheduleFragment = new ClassScheduleFragment();
    private InfoFragment infoFragment = new InfoFragment();
    private QuanziFragment quanziFragment = new QuanziFragment();
    private Fragment currentFragment = new Fragment();

    // 底部导航栏文字
    TextView classScheduleTextView;
    TextView quanziTextView;
    TextView funcTextView;
    TextView infoTextView;
    // 底部导航栏图片
    ImageView classScheduleImg;
    ImageView quanziImg;
    ImageView funcImg;
    ImageView infoImg;
    //底部导航栏
    LinearLayout classScheduleNav;
    LinearLayout quanziNav;
    LinearLayout funcNav;
    LinearLayout infoNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        showFragment(classScheduleFragment).commit();
    }

    //
    private void initView() {
        classScheduleNav = this.findViewById(R.id.ll_nav_class_schedule);
        quanziNav = this.findViewById(R.id.ll_nav_quanzi);
        funcNav = this.findViewById(R.id.ll_nav_func);
        infoNav = this.findViewById(R.id.ll_nav_myself);

        funcTextView = this.findViewById(R.id.tv_nav_func);
        classScheduleTextView = this.findViewById(R.id.tv_nav_class_schedule);
        quanziTextView = this.findViewById(R.id.tv_nav_quanzi);
        infoTextView = this.findViewById(R.id.tv_nav_myself);

        classScheduleImg = this.findViewById(R.id.iv_nav_class_schedule);


        quanziImg = this.findViewById(R.id.iv_nav_quanzi);
        funcImg = this.findViewById(R.id.iv_nav_func);
        infoImg = this.findViewById(R.id.iv_nav_myself);

        initImageColor();
        int nav_selected_color = getResources().getColor(R.color.nav_selected_color);
        classScheduleImg.setColorFilter(nav_selected_color);
    }

    // 初始化监听器
    private void initEvent() {
        classScheduleNav.setOnClickListener(this);
        quanziNav.setOnClickListener(this);
        funcNav.setOnClickListener(this);
        infoNav.setOnClickListener(this);
    }

    // 点击事件
    @Override
    public void onClick(View view) {
        initImageColor();
        int nav_selected_color = getResources().getColor(R.color.nav_selected_color);
        switch (view.getId()) {
            case R.id.ll_nav_func:
                funcImg.setColorFilter(nav_selected_color);
                showFragment(funcFragment).commit();
                break;
            case R.id.ll_nav_class_schedule:
                classScheduleImg.setColorFilter(nav_selected_color);
                showFragment(classScheduleFragment).commit();
                break;
            case R.id.ll_nav_quanzi:
                quanziImg.setColorFilter(nav_selected_color);
                showFragment(quanziFragment).commit();
                break;
            case R.id.ll_nav_myself:
                infoImg.setColorFilter(nav_selected_color);
                showFragment(infoFragment).commit();
                break;
        }

    }
    // 初始化图片颜色
    private void initImageColor() {
        int nav_unselected_color = getResources().getColor(R.color.nav_unselected_color);
        classScheduleImg.setColorFilter(nav_unselected_color);
        funcImg.setColorFilter(nav_unselected_color);
        infoImg.setColorFilter(nav_unselected_color);
        quanziImg.setColorFilter(nav_unselected_color);
    }
    /**
     * 展示指定Fragment
     *
     * @param targetFragment
     * @return
     */
    private FragmentTransaction showFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用showFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fragment_content, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

}
