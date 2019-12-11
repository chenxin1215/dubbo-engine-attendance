package com.xx.attendance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.attendance.dto.requset.OvertimeRequest;
import com.xx.attendance.entity.OvertimeInfo;

import java.util.List;

public interface OvertimeInfoMapper extends BaseMapper<OvertimeInfo> {

    List<OvertimeInfo> queryOvertimeInfoByParam(OvertimeRequest overtimeRequest);

}