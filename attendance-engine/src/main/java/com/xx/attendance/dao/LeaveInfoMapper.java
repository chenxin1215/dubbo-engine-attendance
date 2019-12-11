package com.xx.attendance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.attendance.dto.requset.QueryLeaveListParam;
import com.xx.attendance.entity.LeaveInfo;

import java.util.List;

public interface LeaveInfoMapper extends BaseMapper<LeaveInfo> {

    List<LeaveInfo> queryLeaveListByParam(QueryLeaveListParam param);

}