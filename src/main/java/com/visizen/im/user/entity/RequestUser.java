package com.visizen.im.user.entity;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/9.
 */
public class RequestUser {

    private Long rId;

    private User fromUser;//请求者

    private Integer fromStatus;//请求者消息状态

    private User toUser;//接收者

    private Integer toStatus;//接收者消息状态

    private Integer rResult;//请求结果

    private Date createTime;//请求时间

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Integer fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public Integer getrResult() {
        return rResult;
    }

    public void setrResult(Integer rResult) {
        this.rResult = rResult;
    }
}
