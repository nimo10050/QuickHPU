package com.iot.quickhpu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import static com.iot.quickhpu.utils.CommonUtils.hideInputMethod;
import static com.iot.quickhpu.utils.CommonUtils.isShouldHideInput;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if (hideInputMethod(this, v)) {
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
