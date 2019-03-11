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
        // TODO Auto-generated method stub
        UserBase userBase = userBaseMapper.selectByPrimaryKey(userId);
        return userBase;
    }

    @Override
    public UserBase queryUserByMobile(String mobile) {
        // TODO Auto-generated method stub
        UserBase userBase = userBaseMapper.queryUserByMobile(mobile);
        return userBase;
    }

    @Override
    public UserBase queryUserByUserName(String userName) {
        // TODO Auto-generated method stub
        UserBase userBase = userBaseMapper.queryUserByUserName(userName);
        return userBase;
    }

    @Override
    public LoginStatusEnum userLogin(UserBase userBase) {
        UserBase result = userBaseMapper.queryUserByUserName(userBase.getUserName());
        if (result == null) {
            return LoginStatusEnum.ACCOUNT_NULL;
        } else if (result.getUserStatus() == -1) {
            return LoginStatusEnum.ACCOUNT_DISABLE;
        } else if (result.getPassword() == userBase.getPassword()) {
            return LoginStatusEnum.LOGIN_SUCCESS;
        } else if (result.getPassword() != userBase.getPassword()) {
            return LoginStatusEnum.PWD_ERROR;
        } else {
            return LoginStatusEnum.SYS_ERROR;
        }
    }

    @Override
    public UserBase queryUserByNickName(String nickName) {
        // TODO Auto-generated method stub
        UserBase userBase = userBaseMapper.queryUserByNickName(nickName);
        return userBase;
    }

    @Override
    public void updateUserById(UserBase model) {
        userBaseMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Long saveUser(UserBase model) {
        // TODO Auto-generated method stub
        Long id = (long)userBaseMapper.insertSelective(model);
        return id;
    }

    @Override
    public void deleteUser(Long userId) {
        // TODO Auto-generated method stub
        userBaseMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void resetPassword(String encode, Long userId) {
        // TODO Auto-generated method stub
        UserBase userBase = new UserBase();
        userBase.setPassword(encode);
        userBase.setUserId(userId);
        userBaseMapper.updateByPrimaryKeySelective(userBase);
    }

    @Override
    public List<UserBase> getAllUserList() {
        // TODO Auto-generated method stub
        List<UserBase> result = userBaseMapper.getAllUserList();
        return result;
    }

}
