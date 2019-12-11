package com.xx.attendance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.attendance.dto.requset.attendance.QueryAttendanceInfoParam;
import com.xx.attendance.entity.AttendanceInfo;

import java.util.List;

public interface AttendanceInfoMapper extends BaseMapper<AttendanceInfo> {

    List<AttendanceInfo> queryAttendanceByParam(QueryAttendanceInfoParam param);

}