package com.xx.attendance.server;

import com.xx.attendance.dto.requset.employee.InsertEmployeeRequest;
import com.xx.attendance.dto.requset.employee.QueryEmployeeListParam;
import com.xx.attendance.dto.response.EmployeeDetail;
import com.xx.attendance.entity.EmployeeInfo;

import java.util.List;

public interface EmployeeService {

    /**
     * 多条件查询员工列表
     * 
     * @return
     */
    List<EmployeeDetail> queryEmployeeListByParam(QueryEmployeeListParam param);

    int queryEmployeeListByParamCount(QueryEmployeeListParam param);

    EmployeeDetail getEmployeeById(Long employeeId);

    EmployeeInfo getEmployeeBySn(String employeeSn);

    Long insertEmployee(InsertEmployeeRequest request);

    void updateById(EmployeeInfo employeeInfo);

    /**
     * 开除员工
     */
    void outEmployee(Long employeeId);

    Long getNewEmployeeSn();

}
