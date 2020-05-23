package com.xulu.service;


import com.xulu.model.*;

import java.util.List;


/**
 * @author xuluuy
 * @create 2020-01-18 11:53
 */
public interface UserService {
    //用户登录
    User getByUserName(String username);
    //根据用户名与用户类型查询员工
    int findByUnameAndUtype(User user);
    //根据用户名与enabled查询员工
    int queryIsEnabled(User user);
    //通过username查找员工用户是否存在
    boolean isExist(String username);
    //添加用户信息
    int add(User user);
    //查询用户列表
    List<User> list();
    //查询角色列表
    List<AdminRole> listroles();
    //根据u_id查询r_id
    List<AdminUserRole> listUserRole(String u_id);
    //更新用户信息
    int update(User requestUser);
    //重置更改密码
    int updatePassword(User requestUser);
    //删除用户
    int deleteUser(String u_id);
    //更新用户可用状态
    int updateUstatus(User user);
    //重置密码
    int resetPassword (User requestUser);

    //根据uname查询user
    User queryUserByUname(String u_username);
    //根据u_username查询reg_id
    Cost queryRegId(String u_username);
}
