package com.cx.login.server;

import java.util.List;

import com.cx.login.entity.UserBase;
import com.cx.login.entity.common.LoginStatusEnum;

/**
 * 
 * @author cx
 * @date 2019/03/02
 */
public interface UserService {

    /**
     * 根据Id查询用户
     * 
     * @param userId
     * @return
     */
    UserBase queryUserById(Long userId);

    /**
     * 根据手机查询用户
     * 
     * @param mobile
     * @return
     */

    UserBase queryUserByMobile(String mobile);

    /**
     * 根据登录名查询用户
     * 
     * @param userName
     * @return
     */

    UserBase queryUserByUserName(String userName);

    /**
     * 用户登陆
     * 
     * @param userBase
     * @return
     */

    LoginStatusEnum userLogin(UserBase userBase);

    /**
     * 根据昵称查询用户
     * 
     * @param NickName
     * @return
     */
    UserBase queryUserByNickName(String NickName);

    /**
     * 根据用户编号, 更新用户基本信息
     * 
     * @param model
     */
    void updateUserById(UserBase model);

    /**
     * 添加用户
     * 
     * @param model
     * @return
     */
    Long saveUser(UserBase model);

    /**
     * 删除用户, 状态置位-1, 角色关系保留
     * 
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 重置密码
     * 
     * @param encode
     * @param userId
     */
    void resetPassword(String encode, Long userId);

    List<UserBase> getAllUserList();

}
