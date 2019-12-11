package com.xx.attendance.service.impl;

import com.xx.attendance.dao.ConfigureInfoMapper;
import com.xx.attendance.dao.EmployeeInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.dubbo.config.annotation.Service;
import com.xx.attendance.dto.response.ConfigDetail;
import com.xx.attendance.entity.ConfigureInfo;
import com.xx.attendance.entity.EmployeeInfo;
import com.xx.attendance.entity.LogInfo;
import com.xx.attendance.server.ConfigureService;
import com.xx.attendance.utils.DateUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈配置信息service〉
 *
 * @author xx
 * @create 2019/12/8
 * @since 1.0.0
 */
@Service
public class ConfigureServiceImpl implements ConfigureService {

    @Autowired
    private ConfigureInfoMapper configureInfoMapper;

    @Autowired
    private com.xx.attendance.dao.LogInfoMapper LogInfoMapper;

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Override
    @Transactional
    public void updateConfig(ConfigureInfo configureInfo, Long userId) {
        configureInfo.setId(1L);
        configureInfoMapper.updateById(configureInfo);

        EmployeeInfo employeeInfo = employeeInfoMapper.selectById(userId);
        Assert.notNull(employeeInfo, "不存在此操作员！");

        // 添加日志
        LogInfo LogInfo = new LogInfo();
        LogInfo.setEmployeeId(userId);
        LogInfo.setEmployeeName(employeeInfo.getEmployeeName());
        LogInfo.setContent("修改考勤配置信息！");
        LogInfoMapper.insert(LogInfo);
    }

    @Override
    public ConfigDetail getConfig() {
        ConfigDetail detail = new ConfigDetail();
        ConfigureInfo configureInfo = configureInfoMapper.selectById(1L);
        BeanUtils.copyProperties(configureInfo, detail);
        detail.setWorkHoursStr(DateUtil.dateToString(configureInfo.getWorkHours(), DateUtil.TIME_BASE));
        detail.setOwHoursStr(DateUtil.dateToString(configureInfo.getOwHours(), DateUtil.TIME_BASE));
        return detail;
    }

    @Override
    public Integer getTime() {
        // 正常工作时间 每天规定时间上下班（这里按每月30天、四周计算）
        ConfigureInfo config = configureInfoMapper.selectById(1L);
        int restTime;
        if (config.getBreakType().intValue() == 1) {
            restTime = 4;
        } else {
            restTime = 8;
        }
        int day = 30 - restTime;
        Integer normalTime =
            (DateUtil.subHouse(config.getWorkHours(), config.getOwHours(), DateUtil.TIME_BASE) - 2) * day;
        return normalTime;
    }
}