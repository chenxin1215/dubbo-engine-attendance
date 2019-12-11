package com.xx.attendance.server;

import com.xx.attendance.dto.response.LogInfoDetail;

import java.util.List;

public interface LogInfoService {

    List<LogInfoDetail> queryLogInfoList();

}
