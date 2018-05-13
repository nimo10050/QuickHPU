package com.iot.quickhpu.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author m1563
 * @Date 2018/5/8
 * @Description
 */

public class ClassList implements Serializable {


    /**
     * code : 1234
     * classes : [{"name":"\"classxxx\""},{"name":"\"classyyy\""},{"name":"GGG"}]
     */

    private String code;
    private List<ClassesBean> classes;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ClassesBean> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassesBean> classes) {
        this.classes = classes;
    }

    public static class ClassesBean {
        /**
         * name : "classxxx"
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
