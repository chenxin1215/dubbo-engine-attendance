package com.xx.attendance.server;

import com.xx.attendance.dto.requset.QueryLeaveListParam;
import com.xx.attendance.dto.response.LeaveDetail;

import java.util.List;

public interface LeaveService {

    List<LeaveDetail> queryLeaveListByParam(QueryLeaveListParam param);

}
