package com.iot.quickhpu.utils;

import com.iot.quickhpu.pojo.EmptyClassroom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/3
 * @Description
 */

public class EmptyClassroomJsonUtils {

    public static List<EmptyClassroom> jsonToList(String json) {

        List<EmptyClassroom> gradeList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);

            System.out.println(">>>>>>Json 转换  " + jsonObject.getString("1"));
            for (int i = 1; i <= 5; i++) {
                EmptyClassroom emptyClassroom = new EmptyClassroom();
                emptyClassroom.setClassNumber("第 " + i + "大节");
                emptyClassroom.setEmptyClassroomNumber(jsonObject.getString(i + ""));
                gradeList.add(emptyClassroom);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gradeList;

    }

}
