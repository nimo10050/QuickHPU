package com.iot.quickhpu.utils;

import com.iot.quickhpu.pojo.Grade;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/3
 * @Description 历年成绩转化为集合
 */

public class GradeAllJsonUtils {

    public static List<List<Grade>> jsonToList(String json){
        List<List<Grade>> gradeAllList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            //int termLength = termArray.length;
            for (int i = 0; i < array.length(); i++) {
                JSONArray temp1 = array.getJSONArray(i);
                //String term = termLength >= i ? termArray[i] : "未知学期";
                List<Grade> gradeList = new ArrayList<>();
                for (int j = 0; j < temp1.length(); j++) {
                    JSONArray temp2 = temp1.getJSONArray(j);
                    Grade grade = new Grade();
                    grade.setCredits(temp2.getString(4));
                    grade.setName(temp2.getString(2));
                    grade.setTotal(temp2.getString(6));
                    gradeList.add(grade);
                }
                gradeAllList.add(gradeList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gradeAllList;
    }



}
