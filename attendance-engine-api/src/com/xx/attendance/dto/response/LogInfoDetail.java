package com.xx.attendance.dto.response;

import com.xx.attendance.entity.LogInfo;

public class LogInfoDetail extends LogInfo {

    /**
     * 创建时间
     */
    private String createTimeStr;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
