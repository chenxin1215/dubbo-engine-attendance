package com.cx.login.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * 
 * @author cx
 * @date 2019/03/04
 */
public class UserBase implements Serializable {

    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机
     */
    private String mobile;

    private Integer userStatus;

    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户头像
     */
    private String headImage;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取用户头像
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * 设置用户头像
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "UserBase [userId=" + userId + ", userName=" + userName + ", nickName=" + nickName + ", mobile=" + mobile
            + ", userStatus=" + userStatus + ", password=" + password + ", createTime=" + createTime + ", updateTime="
            + updateTime + ", headImage=" + headImage + "]";
    }
}