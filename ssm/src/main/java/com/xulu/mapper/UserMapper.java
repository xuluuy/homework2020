package com.xulu.mapper;


import com.xulu.model.*;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 11:57
 */
public interface UserMapper {
    //根据员工姓名查询员工
    User findByUserName(String username);
    //根据用户名与用户类型查询员工
    int findByUnameAndUtype(User user);
    //根据username enabled 查询员工
    int queryIsEnabled(User user);
    //添加用户
    int addUser(User user);
    //查找最大用户id
    int queryMaxUid();
    //添加用户-角色
    int addUserRole(AdminUserRole userRole);
    //查询所有用户
    List<User> list();
    //查询所有角色
    List<AdminRole> listroles();
    //更新用户
    int updateUser(User user);
    //更新用户-角色
    int updateUserRole(AdminUserRole userRole);
    //更新密码
    int updatePassword(User user);
    //删除用户
    int deleteUser(String u_id);
    //更新用户状态
    int updateUstatus(User user);

}
