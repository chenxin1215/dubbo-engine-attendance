package com.xx.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * employee_info
 * 
 * @author
 */
public class EmployeeInfo implements Serializable {
    /**
     * 员工编号
     */
    @TableId(value = "employee_id", type = IdType.AUTO) // 指定自增策略
    private Long employeeId;

    /**
     * 工号
     */
    private String employeeSn;

    /**
     * 姓名
     */
    private String employeeName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 1：男；0：女
     */
    private Integer sex;

    /**
     * 入职日期
     */
    private Date enterDate;

    /**
     * 职位 1-CEO 2-总监 3-主管 4-普通员工
     */
    private Integer employeePosition;

    /**
     * 员工类型 1：普通员工；2：管理员
     */
    private Integer employeeType;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 员工状态 1： 在职； 2：离职
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取员工编号
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * 设置员工编号
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * 获取工号
     */
    public String getEmployeeSn() {
        return employeeSn;
    }

    /**
     * 设置工号
     */
    public void setEmployeeSn(String employeeSn) {
        this.employeeSn = employeeSn;
    }

    /**
     * 获取姓名
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 设置姓名
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * 获取年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别 1：男；0：女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 1：男；0：女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取入职日期
     */
    public Date getEnterDate() {
        return enterDate;
    }

    /**
     * 设置入职日期
     */
    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Integer getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(Integer employeePosition) {
        this.employeePosition = employeePosition;
    }

    /**
     * 获取员工类型 1：普通员工；2：管理员
     */
    public Integer getEmployeeType() {
        return employeeType;
    }

    /**
     * 设置员工类型 1：普通员工；2：管理员
     */
    public void setEmployeeType(Integer employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取联系方式
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系方式
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取员工状态 1： 在职； 2：离职
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置员工状态 1： 在职； 2：离职
     */
    public void setState(Integer state) {
        this.state = state;
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
     * 获取修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}