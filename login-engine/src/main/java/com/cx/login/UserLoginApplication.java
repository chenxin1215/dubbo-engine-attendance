package com.cx.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@EnableAsync
@DubboComponentScan("com.cx.login.service")
@MapperScan("com.cx.login.dao")
public class UserLoginApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserLoginApplication.class, args);
    }

}