package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author m1563
 * @Date 2018/4/29
 * @Description
 */

public class EmptyClassroom implements Serializable{

    private String classNumber;
    private String emptyClassroomNumber;


    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getEmptyClassroomNumber() {
        return emptyClassroomNumber;
    }

    public void setEmptyClassroomNumber(String emptyClassroomNumber) {
        this.emptyClassroomNumber = emptyClassroomNumber;
    }
}
