package com.iot.quickhpu.utils;

import com.alibaba.fastjson.JSONArray;
import com.iot.quickhpu.pojo.Student;

/**
 * @Author m1563
 * @Date 2018/5/1
 * @Description
 */

public class InfoJsonUtils {

    public static Student jsonToObject(String json){
        JSONArray arr = JSONArray.parseArray(json);
        Student student = new Student();
        for (int i=0;i<arr.size();i++){
            student.setName(arr.get(1).toString());
            student.setInstitute(arr.get(12).toString());
            student.setMajor(arr.get(13).toString());
            student.setIdCard(arr.get(0).toString());// TODO 换成学生证号
            student.setSex(arr.get(3).toString());
            student.setGrade(arr.get(14).toString());
            student.setStudentClass(arr.get(15).toString());
        }
        return student;
    }


}
