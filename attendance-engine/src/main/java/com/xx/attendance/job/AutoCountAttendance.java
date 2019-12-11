package com.xx.attendance.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xx.attendance.dto.requset.attendance.QueryAttendanceInfoParam;
import com.xx.attendance.dto.requset.employee.QueryEmployeeListParam;
import com.xx.attendance.dto.response.ConfigDetail;
import com.xx.attendance.dto.response.EmployeeDetail;
import com.xx.attendance.dto.response.attendance.AttendanceDetail;
import com.xx.attendance.entity.AttendanceInfo;
import com.xx.attendance.enums.*;
import com.xx.attendance.server.AttendanceService;
import com.xx.attendance.server.ConfigureService;
import com.xx.attendance.server.EmployeeService;
import com.xx.attendance.utils.DateUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class AutoCountAttendance {

    @Reference
    private EmployeeService employeeService;

    @Reference
    private AttendanceService attendanceService;

    @Reference
    private ConfigureService configureService;

    /**
     * 功能描述: 每日凌晨自动计算当日考勤信息
     *
     * @Author: chenxin
     * @Date: 2019/12/9
     */
    @Scheduled(cron = "${job.autoCountAttendance:30 59 23 * * ?}")
    public void autoCountAttendance() {

        Date nowDate = new Date();
        String dateStr = DateUtil.dateToString(nowDate, DateUtil.DATE_BASE);

        // 判断是否休息
        int dayByWeek = DateUtil.getDayByWeek(nowDate);
        ConfigDetail config = configureService.getConfig();
        if (config.getBreakType().intValue() == BreakTypeEnum.SINGLE.value()) {
            if (dayByWeek == 7) {
                return;
            }
        } else if (config.getBreakType().intValue() == BreakTypeEnum.DOUBLE.value()) {
            if (dayByWeek == 6 || dayByWeek == 7) {
                return;
            }
        } else {
            return;
        }

        QueryEmployeeListParam employeeListParam = new QueryEmployeeListParam();
        employeeListParam.setState(EmployeeStateEnum.ONJOB.value());
        List<EmployeeDetail> employeeList = employeeService.queryEmployeeListByParam(employeeListParam);
        if (CollectionUtils.isEmpty(employeeList)) {
            System.out.println("找不到员工");
            return;
        }

        for (EmployeeDetail employee : employeeList) {
            QueryAttendanceInfoParam param = new QueryAttendanceInfoParam();
            param.setEmployeeId(employee.getEmployeeId());
            param.setAttendanceDate(dateStr);
            AttendanceDetail attendanceDetailByParam = attendanceService.getAttendanceDetailByParam(param);
            // 员工没有打卡记录 添加旷工记录
            if (attendanceDetailByParam == null) {
                AttendanceInfo attendanceInfo = new AttendanceInfo();
                attendanceInfo.setEmployeeId(employee.getEmployeeId());
                attendanceInfo.setEmployeeName(employee.getEmployeeName());
                attendanceInfo.setPunchInState(PunchInStateEnum.LESS.value());
                attendanceInfo.setPunchOutState(PunchOutStateEnum.LESS.value());
                attendanceInfo.setRecordYear(DateUtil.getYear(nowDate));
                attendanceInfo.setRecordMonth(DateUtil.getMonth(nowDate));
                attendanceInfo.setRecordDay(DateUtil.getDay(nowDate));
                attendanceInfo.setState(AttendanceStateEnum.ABSENTEEISM.value());
                attendanceInfo.setWorkTime(0);
                attendanceService.insertAttendance(attendanceInfo);
            }
        }

    }

}
