package com.xx.attendance.server;

/**
 * @author xx
 * @since 2019/12/8 17:18
 */

import com.xx.attendance.dto.response.ConfigDetail;
import com.xx.attendance.entity.ConfigureInfo;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xx
 * @create 2019/12/8
 * @since 1.0.0
 */
public interface ConfigureService {

    void updateConfig(ConfigureInfo configureInfo, Long userId);

    ConfigDetail getConfig();

    Integer getTime();

}
