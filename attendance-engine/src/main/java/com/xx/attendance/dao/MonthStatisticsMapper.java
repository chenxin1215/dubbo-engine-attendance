package com.xx.attendance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.attendance.dto.requset.attendance.QueryAttendanceInfoParam;
import com.xx.attendance.entity.MonthStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonthStatisticsMapper extends BaseMapper<MonthStatistics> {

    void batchInsert(@Param("list") List<MonthStatistics> monthStatisticList);

    List<MonthStatistics> queryAttendanceMonthByParam(QueryAttendanceInfoParam param);

    int queryAttendanceMonthListByParamCount(QueryAttendanceInfoParam param);

}