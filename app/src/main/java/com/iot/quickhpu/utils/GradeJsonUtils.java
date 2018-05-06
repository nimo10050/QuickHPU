package com.iot.quickhpu.utils;

import com.iot.quickhpu.pojo.Grade;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/3
 * @Description
 */

public class GradeJsonUtils {

    public static List<Grade> jsonToList(String json){

        List<Grade> gradeList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONArray temp = array.getJSONArray(i);
                Grade grade = new Grade();
                grade.setName(temp.getString(2));
                grade.setCredits(temp.getString(4));
                grade.setMax(temp.getString(6));
                grade.setAvg(temp.getString(8));
                grade.setTotal(temp.getString(9));
                grade.setRange(temp.getString(10));
                gradeList.add(grade);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gradeList;

    }

}
