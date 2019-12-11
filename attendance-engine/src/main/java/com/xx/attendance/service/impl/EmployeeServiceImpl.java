package com.xx.attendance.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xx.attendance.dao.EmployeeInfoMapper;
import com.xx.attendance.dto.requset.employee.InsertEmployeeRequest;
import com.xx.attendance.dto.requset.employee.QueryEmployeeListParam;
import com.xx.attendance.dto.response.EmployeeDetail;
import com.xx.attendance.entity.EmployeeInfo;
import com.xx.attendance.enums.EmployeeStateEnum;
import com.xx.attendance.enums.EmployeeTypeEnum;
import com.xx.attendance.enums.PositionTypeEnum;
import com.xx.attendance.server.EmployeeService;
import com.xx.attendance.utils.DateUtil;
import com.xx.attendance.utils.SnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    public List<EmployeeDetail> queryEmployeeListByParam(QueryEmployeeListParam param) {

        // 将String类型的时间转为Date
        param.setEnterDateStart(param.getEnterDateStartStr() == null ? null
            : DateUtil.getInitStart(DateUtil.stringToDate(param.getEnterDateStartStr(), DateUtil.DATETIME_BASE)));
        param.setEnterDateEnd(param.getEnterDateEndStr() == null ? null
            : DateUtil.getInitEnd(DateUtil.stringToDate(param.getEnterDateEndStr(), DateUtil.DATETIME_BASE)));

        // 定义返回数据对象
        List<EmployeeDetail> result = new ArrayList<EmployeeDetail>();

        // 获取员工基本信息
        List<EmployeeInfo> employeeInfoList = employeeInfoMapper.queryEmployeeListByParam(param);

        // 组装详细信息
        for (EmployeeInfo employeeInfo : employeeInfoList) {
            EmployeeDetail detail = new EmployeeDetail();
            BeanUtils.copyProperties(employeeInfo, detail);
            detail.setEnterDateStr(DateUtil.dateToString(employeeInfo.getEnterDate(), DateUtil.DATE_BASE));
            detail.setPositionStr(PositionTypeEnum.parse(employeeInfo.getEmployeePosition()).toString());
            detail.setEmployeeTypeStr(EmployeeTypeEnum.parse(employeeInfo.getEmployeeType()).toString());
            detail.setStateStr(EmployeeStateEnum.parse(employeeInfo.getState()).toString());

            result.add(detail);
        }

        return result;
    }

    @Override
    public int queryEmployeeListByParamCount(QueryEmployeeListParam param) {

        // 将String类型的时间转为Date
        param.setEnterDateStart(param.getEnterDateStartStr() == null ? null
            : DateUtil.getInitStart(DateUtil.stringToDate(param.getEnterDateStartStr(), DateUtil.DATETIME_BASE)));
        param.setEnterDateEnd(param.getEnterDateEndStr() == null ? null
            : DateUtil.getInitEnd(DateUtil.stringToDate(param.getEnterDateEndStr(), DateUtil.DATETIME_BASE)));

        return employeeInfoMapper.queryEmployeeListByParamCount(param);
    }

    public EmployeeDetail getEmployeeById(Long employeeId) {

        // 获取员工基本信息
        EmployeeInfo employeeInfo = employeeInfoMapper.selectById(employeeId);

        // 组装员工信息
        EmployeeDetail employeeDetail = new EmployeeDetail();
        // 复制类
        BeanUtils.copyProperties(employeeInfo, employeeDetail);
        // 职务
        employeeDetail.setPositionStr(PositionTypeEnum.parse(employeeInfo.getEmployeePosition()).toString());
        // 入职日期
        employeeDetail.setEnterDateStr(DateUtil.dateToString(employeeInfo.getEnterDate(), DateUtil.DATE_BASE));
        // 员工身份
        employeeDetail.setEmployeeTypeStr(EmployeeTypeEnum.parse(employeeInfo.getEmployeeType()).toString());
        // 员工在职状态
        employeeDetail.setStateStr(EmployeeStateEnum.parse(employeeInfo.getState()).toString());

        return employeeDetail;
    }

    @Override
    public EmployeeInfo getEmployeeBySn(String employeeSn) {
        if (employeeSn == null) {
            return null;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("employee_sn", employeeSn);
        EmployeeInfo employeeBySn = employeeInfoMapper.getEmployeeBySn(employeeSn);
        return employeeBySn;
    }

    public Long insertEmployee(InsertEmployeeRequest request) {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(request, employeeInfo);
        employeeInfo.setEmployeeSn(SnUtil.createEmployeeSn(this.getNewEmployeeSn()));
        employeeInfo.setPassword("123456");
        employeeInfo.setState(EmployeeStateEnum.ONJOB.value());
        employeeInfoMapper.insert(employeeInfo);
        return employeeInfo.getEmployeeId();
    }

    public void updateById(EmployeeInfo employeeInfo) {
        employeeInfoMapper.updateById(employeeInfo);
    }

    /**
     * 开出员工 将状态改为2
     * 
     * @param employeeId
     */
    public void outEmployee(Long employeeId) {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setEmployeeId(employeeId);
        employeeInfo.setState(EmployeeStateEnum.QUITJOB.value());
        employeeInfoMapper.updateById(employeeInfo);
    }

    public Long getNewEmployeeSn() {
        return employeeInfoMapper.getNewEmployeeSn();
    }
}
