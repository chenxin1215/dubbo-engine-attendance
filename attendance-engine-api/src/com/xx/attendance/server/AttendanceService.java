package com.xx.attendance.server;

import com.xx.attendance.dto.requset.attendance.QueryAttendanceInfoParam;
import com.xx.attendance.dto.response.attendance.AttendanceDetail;
import com.xx.attendance.dto.response.attendance.AttendanceMonthInfo;
import com.xx.attendance.dto.view.SimpleView;
import com.xx.attendance.entity.AttendanceInfo;
import com.xx.attendance.entity.MonthStatistics;

import java.util.List;

public interface AttendanceService {

    SimpleView punchIn(Long employeeId);

    SimpleView punchOut(Long employeeId);

    void updateAttendance(AttendanceInfo attendanceInfo);

    /**
     * 查询某条考勤详情
     * 
     * @param param
     * @return
     */
    AttendanceDetail getAttendanceDetailByParam(QueryAttendanceInfoParam param);

    /**
     * 条件查询考勤列表 按日
     * 
     * @param param
     * @return
     */
    List<AttendanceDetail> queryAttendanceDetailListByParam(QueryAttendanceInfoParam param);

    /**
     * 功能描述: 条件查询考勤列表 按月
     *
     * @Author: xx
     * @Date: 2019/12/9
     */
    List<AttendanceMonthInfo> queryAttendanceMonthListByParam(QueryAttendanceInfoParam param);

    int queryAttendanceMonthListByParamCount(QueryAttendanceInfoParam param);

    /**
     * 功能描述: 重新生成某月考勤信息
     *
     * @Author: xx
     * @Date: 2019/12/9
     */
    Integer generateMonthAttendanceInfo(QueryAttendanceInfoParam param, Long operationUserId);

    Long insertAttendance(AttendanceInfo attendanceInfo);

    MonthStatistics getMonthStatisticsById(Long id);
}
