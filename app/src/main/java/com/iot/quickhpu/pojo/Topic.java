package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author m1563
 * @Date 2018/5/1
 * @Description
 */

public class Topic implements Serializable {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String title;
    private String content;
    private String username;
    private String date;

    public Topic() {
    }

    public Topic(String id,String title, String content, String username, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Topic{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
