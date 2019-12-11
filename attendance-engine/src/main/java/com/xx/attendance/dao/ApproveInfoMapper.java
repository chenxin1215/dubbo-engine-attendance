package com.xx.attendance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.attendance.dto.requset.approve.QueryApproveParam;
import com.xx.attendance.entity.ApproveInfo;

import java.util.List;

public interface ApproveInfoMapper extends BaseMapper<ApproveInfo> {

    List<ApproveInfo> queryApprovalListByParam(QueryApproveParam request);
}