package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.SportGradeAdapter;
import com.iot.quickhpu.pojo.SportGrade;
import com.iot.quickhpu.utils.FileUtils;

import java.util.List;

public class SportGradeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_grade);
        ListView lvSportGrade = findViewById(R.id.lv_sport_grade);
        String s = FileUtils.readJsonFile(this, "sport.json");
        List<SportGrade> sportGrades = JSONArray.parseArray(s, SportGrade.class);
        lvSportGrade.setAdapter(new SportGradeAdapter(sportGrades));
    }
}
