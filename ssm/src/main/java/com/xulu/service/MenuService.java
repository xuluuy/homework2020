package com.xulu.service;

import com.xulu.model.AdminMenu;
import com.xulu.model.AdminRoleMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 14:28
 */

public interface MenuService {
    //根据当前用户查询出所有菜单项
    List<AdminMenu> getMenusByCurrentUser();
    //通过parentid获取菜单
    List<AdminMenu> getAllByParentId(String parentId) ;

    //查询全部菜单 并以树形结构展示
    List<AdminMenu> getMenusByRoleId(String r_id);
    //处理树形结构
     void handleMenus(List<AdminMenu> menus);
     //根据r_id删除所有角色-菜单
     void deleteAllByRid(String r_id);
    //更新角色-菜单
    void updateRoleMenu(AdminRoleMenu ruleMenu);


}
