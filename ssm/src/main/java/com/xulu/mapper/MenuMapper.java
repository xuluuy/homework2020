package com.xulu.mapper;

import com.xulu.model.AdminMenu;
import com.xulu.model.AdminRole;
import com.xulu.model.AdminRoleMenu;
import com.xulu.model.AdminUserRole;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 14:32
 */
public interface MenuMapper {
    //用户菜单查询
    AdminMenu findMenuById(String id);
    //通过parentid查询
    List<AdminMenu> findMenuAllByParentId(String parentId);

    //用户角色查询
    AdminRole findRoleById(String id);
    //查询所有角色-菜单通过角色id
    List<AdminRoleMenu> findAllRMByRid(String r_id);
    //通过rid删除角色-菜单关系
    void deleteAllRMByRid(String r_id);
    //通过uid查询所有用户-角色关系
    List<AdminUserRole> findAllByUid(String u_id);
    //通过uid删除关系
    void deleteAllByUid(String u_id);
    //更新角色-菜单
    void updateRRoleMenu(AdminRoleMenu roleMenu);
    //获取角色最大id
    int queryMaxRid();
    //添加角色
    int createRole(AdminRole role);
}
