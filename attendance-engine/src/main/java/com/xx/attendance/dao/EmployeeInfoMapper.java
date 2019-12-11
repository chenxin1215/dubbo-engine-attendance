package com.xx.attendance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.attendance.dto.requset.employee.QueryEmployeeListParam;
import com.xx.attendance.entity.EmployeeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {

    Long getNewEmployeeSn();

    EmployeeInfo getEmployeeBySn(@Param("employeeSn") String employeeSn);

    List<EmployeeInfo> queryEmployeeListByParam(QueryEmployeeListParam param);

    int queryEmployeeListByParamCount(QueryEmployeeListParam param);

}