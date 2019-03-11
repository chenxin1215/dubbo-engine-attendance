package com.cx.login.dao;

import java.util.List;

import com.cx.login.entity.UserBase;

/**
 * UserBaseMapper继承基类
 */
public interface UserBaseMapper extends BaseMapper<UserBase, Long> {

    public UserBase queryUserByMobile(String mobile);

    public UserBase queryUserByUserName(String userName);

    public UserBase queryUserByNickName(String nickName);

    public List<UserBase> getAllUserList();

}