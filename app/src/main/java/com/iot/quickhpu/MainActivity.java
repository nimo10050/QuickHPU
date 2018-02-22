package com.iot.quickhpu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.quickhpu.fragment.HomeFragment;
import com.iot.quickhpu.fragment.LessonFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private HomeFragment home = new HomeFragment();
    private LessonFragment lesson = new LessonFragment();
    private Fragment currentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showFragment(home).commit();
    }

    /**
     * 初始化 view
     */
    private void initView() {
        TextView navHome = this.findViewById(R.id.tv_nav_home);
        TextView navLesson = this.findViewById(R.id.tv_nav_lesson);
        TextView navFriend = this.findViewById(R.id.tv_nav_friend);
        TextView navSelf = this.findViewById(R.id.tv_nav_self);
        navHome.setOnClickListener(this);
        navLesson.setOnClickListener(this);
        navFriend.setOnClickListener(this);
        navSelf.setOnClickListener(this);
    }

    /**
     * 底部导航切点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_nav_home:
                showFragment(home).commit();
                break;
            case R.id.tv_nav_lesson:
                showFragment(lesson).commit();
                break;
            case R.id.tv_nav_friend:
                Toast.makeText(this,"朋友圈",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_nav_self:
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
