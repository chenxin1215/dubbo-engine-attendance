package com.cx.login;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cx.login.entity.UserBase;
import com.cx.login.entity.common.LoginStatusEnum;
import com.cx.login.server.UserService;

/**
 * 用户接口测试
 * 
 * @author cx
 * @date 2019/03/09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    UserService userService;

    @Test
    public void testSaveUser() {
        LOGGER.info("############ 用户接口测试 #############");

        UserBase userBase = new UserBase();
        userBase.setCreateTime(new Date());
        userBase.setUserName("chenxin");
        userBase.setNickName("猪皮恶霸");
        userBase.setMobile("13973341321");

        LOGGER.info("插入测试用例：{}", userBase);

        userService.saveUser(userBase);

    }

    @Test
    public void testQueryUser() {
        LOGGER.info("############ 用户接口测试 #############");

        UserBase result = userService.queryUserByUserName("chenxin");

        LOGGER.info("查询测试结果：{}", result.toString());
        System.out.println("查询测试结果：" + result.toString());
    }

    @Test
    public void testUserLogin() {
        LOGGER.info("############ 用户登陆测试 #############");

        UserBase userBase = new UserBase();
        userBase.setUserName("chenxin");
        userBase.setPassword("123456");

        LoginStatusEnum result = userService.userLogin(userBase);

        LOGGER.info("登陆结果：{}", result.toString());
        System.out.println("登陆结果：" + result);
    }

}
