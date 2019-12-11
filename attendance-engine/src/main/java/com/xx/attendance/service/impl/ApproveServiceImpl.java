package com.xx.attendance.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xx.attendance.dao.*;
import com.xx.attendance.dto.requset.approve.ApprovalRequest;
import com.xx.attendance.dto.requset.approve.QueryApproveParam;
import com.xx.attendance.dto.response.approve.ApproveInfoData;
import com.xx.attendance.entity.*;
import com.xx.attendance.enums.ApproveStateEnum;
import com.xx.attendance.enums.ApproveTypeEnum;
import com.xx.attendance.server.ApproveService;
import com.xx.attendance.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApproveServiceImpl implements ApproveService {

    @Autowired
    private ApproveInfoMapper approveInfoMapper;

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Autowired
    private OvertimeInfoMapper overtimeInfoMapper;

    @Autowired
    private LeaveInfoMapper leaveInfoMapper;

    @Autowired
    private LogInfoMapper LogInfoMapper;

    public void addApprove(ApprovalRequest request) {

        EmployeeInfo employeeInfo = employeeInfoMapper.selectById(request.getApprovalUserId());

        Assert.notNull(employeeInfo, "审批发起人不存在！");
        Assert.notNull(request.getApprovalType(), "审批类型不能为空！");
        Assert.notNull(request.getStartDateStr(), "开始时间不能为空！");
        Assert.notNull(request.getEndDateStr(), "结束时间不能为空！");

        ApproveInfo approveInfo = new ApproveInfo();
        BeanUtils.copyProperties(request, approveInfo);
        approveInfo.setEmployeeId(employeeInfo.getEmployeeId());
        approveInfo.setEmployeeName(employeeInfo.getEmployeeName());
        approveInfo.setStartDate(DateUtil.stringToDate(request.getStartDateStr(), DateUtil.DATE_BASE));
        approveInfo.setEndDate(DateUtil.stringToDate(request.getEndDateStr(), DateUtil.DATE_BASE));
        approveInfo.setApproveReason(request.getApprovalContent());
        approveInfo.setApproveState(ApproveStateEnum.NO_CHECK.value());

        approveInfoMapper.insert(approveInfo);
    }

    public List<ApproveInfoData> queryApprovalListByParam(QueryApproveParam request) {
        List<ApproveInfoData> dataList = new ArrayList<ApproveInfoData>();
        List<ApproveInfo> approveInfoList = approveInfoMapper.queryApprovalListByParam(request);
        for (ApproveInfo approveInfo : approveInfoList) {
            ApproveInfoData data = new ApproveInfoData();
            BeanUtils.copyProperties(approveInfo, data);
            if (approveInfo.getApproveTime() != null) {
                data.setApproveTimeStr(DateUtil.dateToString(approveInfo.getApproveTime(), DateUtil.DATETIME_BASE));
            }
            data.setCreateTimeStr(DateUtil.dateToString(approveInfo.getApproveTime(), DateUtil.DATETIME_BASE));
            data.setApproveStateStr(ApproveStateEnum.parse(approveInfo.getApproveState()).toString());
            data.setApproveTypeStr(ApproveTypeEnum.parse(approveInfo.getApproveType()).toString());
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * 通过审批
     * 
     * @param approveId
     * @param checkUserId
     */
    @Transactional
    public void passApproval(Long approveId, Long checkUserId) {
        ApproveInfo approveInfo = approveInfoMapper.selectById(approveId);
        EmployeeInfo checkUser = employeeInfoMapper.selectById(checkUserId);
        Assert.notNull(approveInfo, "需要审核的审批不存在");
        Assert.notNull(checkUser, "审核人不存在！");

        approveInfo.setApproveState(ApproveStateEnum.CHECKED.value());
        approveInfo.setApproveUserId(checkUser.getEmployeeId());
        approveInfo.setApprovePerson(checkUser.getEmployeeName());
        approveInfo.setApproveTime(new Date());

        if (approveInfo.getApproveState().intValue() == ApproveTypeEnum.OVERTIME.value()) {
            // 添加对应的加班信息
            OvertimeInfo overtimeInfo = new OvertimeInfo();
            overtimeInfo.setEmployeeId(approveInfo.getEmployeeId());
            overtimeInfo.setOverDate(approveInfo.getStartDate());
            overtimeInfo.setOverReason(approveInfo.getApproveReason());
            overtimeInfo.setApproveUserId(approveInfo.getApproveId());
            overtimeInfo.setApproveTime(new Date());
            overtimeInfo.setCompleteState(0);
            overtimeInfoMapper.insert(overtimeInfo);

            approveInfo.setOvertimeId(overtimeInfo.getOvertimeId());
        } else if (approveInfo.getApproveState().intValue() == ApproveTypeEnum.LEAVE.value()) {
            // 添加对应的请假记录
            LeaveInfo leaveInfo = new LeaveInfo();
            leaveInfo.setEmployeeId(approveInfo.getEmployeeId());
            leaveInfo.setLeaveReason(approveInfo.getApproveReason());
            leaveInfo.setStartDate(approveInfo.getStartDate());
            leaveInfo.setEndDate(approveInfo.getEndDate());
            leaveInfo.setApproveUserId(approveInfo.getApproveUserId());
            leaveInfo.setApproveTime(new Date());
            leaveInfoMapper.insert(leaveInfo);

            approveInfo.setLeaveId(leaveInfo.getId());
        }
        approveInfoMapper.updateById(approveInfo);

        // 添加日志
        LogInfo LogInfo = new LogInfo();
        LogInfo.setEmployeeId(checkUser.getEmployeeId());
        LogInfo.setEmployeeName(checkUser.getEmployeeName());
        LogInfo.setContent("通过审批! 审批类型：" + ApproveTypeEnum.parse(approveInfo.getApproveState()).toString() + ", 审批人："
            + checkUser.getEmployeeName());
        LogInfoMapper.insert(LogInfo);
    }

    @Transactional
    public void refusedApproval(Long approveId, Long checkUserId) {
        ApproveInfo approveInfo = approveInfoMapper.selectById(approveId);
        EmployeeInfo checkUser = employeeInfoMapper.selectById(checkUserId);
        Assert.notNull(approveInfo, "需要审核的审批不存在");
        Assert.notNull(checkUser, "审核人不存在！");

        approveInfo.setApproveState(ApproveStateEnum.REFUSED.value());
        approveInfo.setApproveUserId(checkUser.getEmployeeId());
        approveInfo.setApprovePerson(checkUser.getEmployeeName());
        approveInfo.setApproveTime(new Date());
        approveInfoMapper.updateById(approveInfo);

        // 添加日志
        LogInfo LogInfo = new LogInfo();
        LogInfo.setEmployeeId(checkUser.getEmployeeId());
        LogInfo.setEmployeeName(checkUser.getEmployeeName());
        LogInfo.setContent("拒绝审批! 审批类型：" + ApproveTypeEnum.parse(approveInfo.getApproveState()).toString() + ", 审批人："
            + checkUser.getEmployeeName());
        LogInfoMapper.insert(LogInfo);
    }
}
