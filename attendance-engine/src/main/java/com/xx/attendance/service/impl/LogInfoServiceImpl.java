package com.xx.attendance.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xx.attendance.dto.response.LogInfoDetail;
import com.xx.attendance.entity.LogInfo;
import com.xx.attendance.server.LogInfoService;
import com.xx.attendance.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private com.xx.attendance.dao.LogInfoMapper LogInfoMapper;

    @Override
    public List<LogInfoDetail> queryLogInfoList() {

        List<LogInfoDetail> result = new ArrayList<>();
        List<LogInfo> LogInfos = LogInfoMapper.selectList(null);
        for (LogInfo LogInfo : LogInfos) {
            LogInfoDetail detail = new LogInfoDetail();
            BeanUtils.copyProperties(LogInfo, detail);
            detail.setCreateTimeStr(DateUtil.dateToString(LogInfo.getCreateTime(), DateUtil.DATETIME_BASE));
            result.add(detail);
        }
        return result;
    }
}
