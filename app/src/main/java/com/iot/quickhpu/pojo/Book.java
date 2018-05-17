package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author m1563
 * @Date 2018/5/17
 * @Description 图书实体类
 */

public class Book implements Serializable {

    private String name;
    private String auth;
    private String date;
    private String pubilsh;

    public Book() {
    }

    public Book(String name, String auth, String date, String pubilsh) {
        this.name = name;
        this.auth = auth;
        this.date = date;
        this.pubilsh = pubilsh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPubilsh() {
        return pubilsh;
    }

    public void setPubilsh(String pubilsh) {
        this.pubilsh = pubilsh;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", auth='" + auth + '\'' +
                ", date='" + date + '\'' +
                ", pubilsh='" + pubilsh + '\'' +
                '}';
    }
}
