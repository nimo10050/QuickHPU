package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author: LAL
 * @Description:
 * @Date Created on 2018/5/6.
 */
public class Comment implements Serializable {

    // 评论人id
    private String userId;
    // 话题id
    private String topicId;
    // 评论内容
    private String commentContent;
    // 评论时间
    private String commentDate;
    // 评论人
    private String username;

    public Comment() {
    }

    public Comment(String userId, String topicId, String commentContent, String commentDate, String username) {
        this.userId = userId;
        this.topicId = topicId;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.username = username;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId='" + userId + '\'' +
                ", topicId='" + topicId + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
