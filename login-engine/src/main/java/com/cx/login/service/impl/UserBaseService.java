package com.cx.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.cx.login.dao.UserBaseMapper;
import com.cx.login.entity.UserBase;
import com.cx.login.entity.common.LoginStatusEnum;
import com.cx.login.server.UserService;

@Service
public class UserBaseService implements UserService {

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Override
    public UserBase queryUserById(Long userId) {
        UserBase userBase = userBaseMapper.selectByPrimaryKey(userId);
        return userBase;
    }

    @Override
    public UserBase queryUserByMobile(String mobile) {
        UserBase userBase = userBaseMapper.queryUserByMobile(mobile);
        return userBase;
    }

    @Override
    public UserBase queryUserByUserName(String userName) {
        UserBase userBase = userBaseMapper.queryUserByUserName(userName);
        return userBase;
    }

    @Override
    public LoginStatusEnum userLogin(UserBase userBase) {
        UserBase result = userBaseMapper.queryUserByUserName(userBase.getUserName());
        System.out.println("登陆用户：:" + userBase.toString());
        if (result == null) {
            return LoginStatusEnum.ACCOUNT_NULL;
        } else if (result.getUserStatus() == -1) {
            return LoginStatusEnum.ACCOUNT_DISABLE;
        } else if (result.getPassword().equals(userBase.getPassword())) {
            System.out.println("查询用户：:" + result.toString());
            return LoginStatusEnum.LOGIN_SUCCESS;
        } else if (!result.getPassword().equals(userBase.getPassword())) {
            System.out.println("查询用户：:" + result.toString());
            return LoginStatusEnum.PWD_ERROR;
        } else {
            return LoginStatusEnum.SYS_ERROR;
        }
    }

    @Override
    public UserBase queryUserByNickName(String nickName) {
        UserBase userBase = userBaseMapper.queryUserByNickName(nickName);
        return userBase;
    }

    @Override
    public void updateUserById(UserBase model) {
        userBaseMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Long saveUser(UserBase model) {
        model.setPassword("123456");
        Long id = (long)userBaseMapper.insertSelective(model);
        return id;
    }

    @Override
    public void deleteUser(Long userId) {
        userBaseMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void resetPassword(String encode, Long userId) {
        UserBase userBase = new UserBase();
        userBase.setPassword(encode);
        userBase.setUserId(userId);
        userBaseMapper.updateByPrimaryKeySelective(userBase);
    }

    @Override
    public List<UserBase> getAllUserList() {
        List<UserBase> result = userBaseMapper.getAllUserList();
        return result;
    }

}
