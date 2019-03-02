package com.cx.login.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author cx
 * @date 2019/03/02
 */
public class UserBaseModel implements Serializable {

    private Long userId;

    private String userName;

    private String passowrd;

    private String nickName;

    private String mobile;

    private Integer userStatus;

    private Date createTime;

    private String headImage;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", passowrd=" + passowrd + ", nickName=" + nickName
            + ", mobile=" + mobile + ", userStatus=" + userStatus + ", createTime=" + createTime + ", headImage="
            + headImage + "]";
    }

}
