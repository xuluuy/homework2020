package com.xulu.service;

import com.xulu.model.AdminRole;

import java.util.ArrayList;

/**
 * @author xuluuy
 * @create 2020-01-23 18:05
 */
public interface RoleService {
    //改变角色状态
    int changeRoleStatus(AdminRole role);
    //更改角色
    void updateRole(AdminRole role);
    //根据r_id 获取m_id
    ArrayList<String> currentMenu(String r_id);
    //创建角色
    int createRole(AdminRole role);
    //删除角色
    int deleteRole (String r_id);
}
