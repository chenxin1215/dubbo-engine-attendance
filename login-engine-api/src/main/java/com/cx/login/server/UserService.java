package com.cx.login.server;

import com.cx.login.entity.UserBaseModel;

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
    UserBaseModel queryUserById(Long userId);

    /**
     * 根据手机查询用户
     * 
     * @param mobile
     * @return
     */
    UserBaseModel queryUserByMobile(String mobile);

    /**
     * 根据登录名查询用户
     * 
     * @param userName
     * @return
     */
    UserBaseModel queryUserByUserName(String userName);

    /**
     * 根据昵称查询用户
     * 
     * @param NickName
     * @return
     */
    UserBaseModel queryUserByNickName(String NickName);

    /**
     * 根据用户编号, 更新用户基本信息
     * 
     * @param model
     */
    void updateUserById(UserBaseModel model);

    /**
     * 添加用户
     * 
     * @param model
     * @return
     */
    Long saveUser(UserBaseModel model);

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

}
