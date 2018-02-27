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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // 容器页面
    private FunctionFragment funcFragment = new FunctionFragment();
    private ClassScheduleFragment classScheduleFragment = new ClassScheduleFragment();
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
        showFragment(funcFragment).commit();
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
         quanziImg= this.findViewById(R.id.iv_nav_quanzi);
         funcImg= this.findViewById(R.id.iv_nav_func);
         infoImg= this.findViewById(R.id.iv_nav_myself);

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

        switch (view.getId()){
            case R.id.tv_nav_func:
                showFragment(funcFragment).commit();
                break;
            case R.id.tv_nav_class_schedule:
                showFragment(classScheduleFragment).commit();
                break;
            case R.id.tv_nav_quanzi:
                Toast.makeText(this,"小圈子",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_nav_myself:
                Toast.makeText(this,"个人",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**
     * 展示指定Fragment
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
            transaction.add(R.id.fragment_content, targetFragment,targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

}
