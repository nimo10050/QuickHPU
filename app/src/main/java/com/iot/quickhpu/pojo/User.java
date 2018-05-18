package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author m1563
 * @Date 2018/5/18
 * @Description
 */

public class User implements Serializable {

    /**
     * name : zhangsan
     * studentId : sss
     * status : 0
     */

    private String name;
    private String studentId;
    private String status;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
