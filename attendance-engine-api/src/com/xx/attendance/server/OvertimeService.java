package com.xx.attendance.server;

import com.xx.attendance.dto.requset.OvertimeRequest;
import com.xx.attendance.dto.response.OvertimeDetail;

import java.util.List;

public interface OvertimeService {

    // 按条件查询加班信息
    List<OvertimeDetail> queryOvertimeInfoByParam(OvertimeRequest overtimeRequest);

}
