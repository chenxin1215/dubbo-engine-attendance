package com.xx.attendance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@EnableAsync
@DubboComponentScan("com.xx.attendance.service")
@MapperScan("com.xx.attendance.dao")
public class AttendanceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AttendanceApplication.class, args);
    }

}