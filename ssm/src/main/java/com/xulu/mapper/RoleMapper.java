package com.xulu.mapper;

import com.xulu.model.AdminRole;

/**
 * @author xuluuy
 * @create 2020-01-23 19:58
 */
public interface RoleMapper {
    //改变角色状态
    int changeRoleStatus(AdminRole role);
    //更改角色
    void updateRole(AdminRole role);
    //删除角色
    int deleteRole(String r_id);
}
